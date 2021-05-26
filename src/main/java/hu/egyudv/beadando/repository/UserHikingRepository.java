package hu.egyudv.beadando.repository;

import hu.egyudv.beadando.repository.db.entity.Hiking;
import hu.egyudv.beadando.repository.db.entity.User;

import java.util.List;

public interface UserHikingRepository {

    List<Hiking> getHikingListByUser(long userId);
    List<User> getUserListByHiking(long hikingId);
    void save(long userId, long hikingId);
    void delete(long userId, long hikingId);
    void deleteAllByUser(long userId);
}
