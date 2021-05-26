package hu.egyudv.beadando.service;

import hu.egyudv.beadando.model.HikingData;
import hu.egyudv.beadando.model.UserData;


import java.util.List;

public interface UserHikingService {

    List<HikingData> getHikingListByUser(long userId);
    List<UserData> getUserListByHiking(long hikingId);
    void save(long userId, long hikingId);
    void delete(long userId, long hikingId);
}
