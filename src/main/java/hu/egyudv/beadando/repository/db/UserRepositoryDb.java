package hu.egyudv.beadando.repository.db;

import hu.egyudv.beadando.exception.EntityNotFoundException;
import hu.egyudv.beadando.repository.UserHikingRepository;
import hu.egyudv.beadando.repository.UserRepository;
import hu.egyudv.beadando.repository.db.entity.User;
import hu.egyudv.beadando.repository.db.entity.UserHiking;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.List;

public class UserRepositoryDb implements UserRepository {


    @Override
    public List<User> all() {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Query q = session.createQuery("select _user from User _user");
        List<User> resultList = q.list();

        session.close();

        return resultList;
    }

    @Override
    public User save(User object) {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public void delete(long id) {
        User user = get(id);

        if (user != null) {
            List<UserHiking> relatedUserHikingList = userHikingRepository.getByUser(id);

            SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (UserHiking item : relatedUserHikingList) {
                session.delete(item);
            }
            session.delete(user);
            session.getTransaction().commit();
            session.close();
        } else {
            throw new EntityNotFoundException("User Not Found - DELETE : " + id);
        }
    }

    @Override
    public User get(long id) {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Query q = session.createQuery("select _user from User _user where id = :id ");
        q.setParameter("id",id);

        List<User> resultList = q.list();

        session.close();

        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }


    @Override
    public List<User> statAgeBetween15And20() {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Query q = session.createQuery("select _user from User _user where _user.birthDate >= '2001.01.01' and _user.birthDate <= '2006.12.31'");
        List<User> resultList = q.list();

        session.close();

        return resultList;
    }

    @Override
    public List<User> statBornInJuly() {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Query q = session.createQuery("select _user from User _user where to_char(_user.birthDate, 'MM') = '07'");
        List<User> resultList = q.list();

        session.close();

        return resultList;
    }

}
