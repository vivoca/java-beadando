package hu.egyudv.beadando.repository;

import hu.egyudv.beadando.repository.db.HikingRepositoryDb;
import hu.egyudv.beadando.repository.db.UserRepositoryDb;
import hu.egyudv.beadando.repository.db.entity.Hiking;
import hu.egyudv.beadando.repository.db.entity.User;
import hu.egyudv.beadando.repository.db.entity.UserHiking;

import java.util.List;

public interface UserHikingRepository {

    HikingRepository hikingRepository = new HikingRepositoryDb();
    UserRepository userRepository = new UserRepositoryDb();

    List<Hiking> getHikingListByUser(long userId);
    List<User> getUserListByHiking(long hikingId);
    void save(long userId, long hikingId);
    void delete(long userId, long hikingId);
    List<UserHiking> getByUser(long userId);
    List<UserHiking> getByHiking(long hikingId);
}
