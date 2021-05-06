package hu.egyudv.beadando.repository;

import hu.egyudv.beadando.repository.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> all();
    User save(User object);
    void delete(String id);
    User get(String id);
}
