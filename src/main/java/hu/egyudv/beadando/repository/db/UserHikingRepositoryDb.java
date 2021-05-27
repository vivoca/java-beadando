package hu.egyudv.beadando.repository.db;

import hu.egyudv.beadando.repository.UserHikingRepository;
import hu.egyudv.beadando.repository.db.entity.Hiking;
import hu.egyudv.beadando.repository.db.entity.User;
import hu.egyudv.beadando.repository.db.entity.UserHiking;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.ArrayList;
import java.util.List;

public class UserHikingRepositoryDb implements UserHikingRepository {

    @Override
    public List<Hiking> getHikingListByUser(long userId) {
        List<Hiking> resultList = new ArrayList<>();

        List<UserHiking> userHikingList = getByUser(userId);
        for (UserHiking item : userHikingList) {
            resultList.add(item.getHiking());
        }

        return resultList;
    }

    @Override
    public List<User> getUserListByHiking(long hikingId) {
        List<User> resultList = new ArrayList<>();

        List<UserHiking> userHikingList = getByHiking(hikingId);
        for (UserHiking item : userHikingList) {
            resultList.add(item.getUser());
        }

        return resultList;
    }

    @Override
    public List<UserHiking> getByUser(long userId) {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Query q = session.createQuery("select _uh from UserHiking _uh where userId = :userId");
        q.setParameter("userId", userId);
        List<UserHiking> resultList = q.list();

        session.close();

        return resultList;
    }

    @Override
    public List<UserHiking> getByHiking(long hikingId) {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Query q = session.createQuery("select _uh from UserHiking _uh where hikingId = :hikingId");
        q.setParameter("hikingId", hikingId);
        List<UserHiking> resultList = q.list();

        session.close();

        return resultList;
    }

    @Override
    public void save(long userId, long hikingId) {
        UserHiking userHiking = get(userId, hikingId);
        if (userHiking == null) {
            User user = userRepository.get(userId);
            Hiking hiking = hikingRepository.get(hikingId);
            userHiking = new UserHiking();
            userHiking.setUser(user);
            userHiking.setHiking(hiking);
            SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(userHiking);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void delete(long userId, long hikingId) {
        UserHiking userHiking = get(userId, hikingId);
        if (userHiking != null) {
            SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(userHiking);
            session.getTransaction().commit();
            session.close();
        }
    }

    private UserHiking get(long userId, long hikingId) {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Query q = session.createQuery("select _uh from UserHiking _uh where user.id = :userId and hiking.id = :hikingId ");
        q.setParameter("userId",userId);
        q.setParameter("hikingId",hikingId);

        List<UserHiking> resultList = q.list();

        session.close();

        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

    @Override
    public List<User> statMoreThan5Hiking() {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Query q = session.createQuery("SELECT distinct _uh.user FROM UserHiking _uh WHERE _uh.user IN (SELECT _uh2.user FROM UserHiking _uh2 GROUP BY user HAVING COUNT(*) >= 5)");

        List<User> resultList = q.list();

        session.close();

        return resultList;
    }

    @Override
    public List<User> statCompletedMediumHike() {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Query q = session.createQuery("select distinct _uh.user from UserHiking _uh where _uh.hiking.difficulty = 'MEDIUM'");

        List<User> resultList = q.list();

        session.close();

        return resultList;
    }
}
