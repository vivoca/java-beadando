package hu.egyudv.beadando.repository;

import hu.egyudv.beadando.repository.entity.Hiking;
import hu.egyudv.beadando.repository.entity.User;

import java.util.List;

public interface UserHikingRepository {

    List<Hiking> getHikingListByUser(String userId);
    List<User> getUserListByHiking(String hikingId);
    void save(String userId, String hikingId);
    void delete(String userId, String hikingId);
    void deleteAllByUser(String userId);
}
