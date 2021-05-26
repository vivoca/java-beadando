package hu.egyudv.beadando.service;

import hu.egyudv.beadando.model.HikingData;
import hu.egyudv.beadando.model.UserData;
import hu.egyudv.beadando.repository.UserHikingRepository;
import hu.egyudv.beadando.repository.db.UserHikingRepositoryDb;
import hu.egyudv.beadando.repository.db.entity.Hiking;
import hu.egyudv.beadando.repository.db.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserHikingServiceImpl implements UserHikingService{

    private final UserHikingRepository userHikingRepository = new UserHikingRepositoryDb();
    private final UserService userService = new UserServiceImpl();
    private final HikingService hikingService = new HikingServiceImpl();

    @Override
    public List<HikingData> getHikingListByUser(long userId) {
        List<Hiking> hikingList = userHikingRepository.getHikingListByUser(userId);
        List<HikingData> resultList = new ArrayList<>();
        for (Hiking object : hikingList) {
            resultList.add(hikingService.convertToHikingData(object));
        }
        return resultList;
    }

    @Override
    public List<UserData> getUserListByHiking(long hikingId) {
        List<User> userList = userHikingRepository.getUserListByHiking(hikingId);
        List<UserData> resultList = new ArrayList<>();
        for (User object : userList) {
            resultList.add(userService.convertToUserData(object));
        }
        return resultList;
    }

    @Override
    public void save(long userId, long hikingId) {
        userHikingRepository.save(userId, hikingId);
    }

    @Override
    public void delete(long userId, long hikingId) {
        userHikingRepository.delete(userId, hikingId);
    }
}
