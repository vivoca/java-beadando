package hu.egyudv.beadando.repository;

import hu.egyudv.beadando.repository.db.UserHikingRepositoryDb;
import hu.egyudv.beadando.repository.db.entity.User;

import java.util.List;

public interface UserRepository {

    UserHikingRepository userHikingRepository = new UserHikingRepositoryDb();
    List<User> all();
    User save(User object);
    void delete(long id);
    User get(long id);
}
