package hu.egyudv.beadando.service;

import hu.egyudv.beadando.repository.entity.Hiking;
import hu.egyudv.beadando.repository.entity.User;

import java.util.List;

public interface UserHikingService {

    List<Hiking> getHikingListByUser(String userId);
    List<User> getUserListByHiking(String hikingId);
    void save(String userId, String hikingId);
    void delete(String userId, String hikingId);
}
