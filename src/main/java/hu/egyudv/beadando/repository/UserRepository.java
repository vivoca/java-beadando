package hu.egyudv.beadando.repository;

import hu.egyudv.beadando.repository.db.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> all();
    User save(User object);
    void delete(long id);
    User get(long id);
}
