package hu.egyudv.beadando.repository.db;

import hu.egyudv.beadando.exception.EntityNotFoundException;
import hu.egyudv.beadando.repository.HikingRepository;
import hu.egyudv.beadando.repository.UserHikingRepository;
import hu.egyudv.beadando.repository.db.entity.Hiking;
import hu.egyudv.beadando.repository.db.entity.User;
import hu.egyudv.beadando.repository.db.entity.UserHiking;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.List;

public class HikingRepositoryDb implements HikingRepository {

    private final UserHikingRepository userHikingRepository = new UserHikingRepositoryDb();

    @Override
    public List<Hiking> all() {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Query q = session.createQuery("select _hiking from Hiking _hiking");
        List<Hiking> resultList = q.list();

        session.close();

        return resultList;
    }

    @Override
    public Hiking save(Hiking object) {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public void delete(long id) {
        Hiking hiking = get(id);

        if (hiking != null) {
            List<UserHiking> relatedUserHikingList = userHikingRepository.getByHiking(id);

            SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (UserHiking item : relatedUserHikingList) {
                session.delete(item);
            }
            session.delete(hiking);
            session.getTransaction().commit();
            session.close();
        } else {
            throw new EntityNotFoundException("Hiking Not Found - DELETE : " + id);
        }
    }

    @Override
    public Hiking get(long id) {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Query q = session.createQuery("select _hiking from Hiking _hiking where id = :id ");
        q.setParameter("id",id);

        List<Hiking> resultList = q.list();

        session.close();

        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

}
