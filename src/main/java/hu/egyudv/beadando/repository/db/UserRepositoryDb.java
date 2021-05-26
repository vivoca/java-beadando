package hu.egyudv.beadando.repository.db;

import hu.egyudv.beadando.repository.UserRepository;
import hu.egyudv.beadando.repository.db.entity.User;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryDb implements UserRepository {
    @Override
    public List<User> all() {
        return new ArrayList<>();
    }

    @Override
    public User save(User object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        if (object.getId() != null) {
            session.merge(object);
        } else {
            session.persist(object);
        }
        session.flush();
        session.getTransaction().commit();
        session.close();
        return object;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public User get(long id) {
        return null;
    }
}
