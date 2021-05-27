package hu.egyudv.beadando.service;

import hu.egyudv.beadando.model.UserData;
import hu.egyudv.beadando.repository.UserHikingRepository;
import hu.egyudv.beadando.repository.UserRepository;
import hu.egyudv.beadando.repository.db.UserHikingRepositoryDb;
import hu.egyudv.beadando.repository.db.UserRepositoryDb;
import hu.egyudv.beadando.repository.db.entity.User;

import java.util.ArrayList;
import java.util.List;

public class StatisticsServiceImpl implements StatisticsService{

    private final UserRepository userRepository = new UserRepositoryDb();
    private final UserHikingRepository userHikingRepository = new UserHikingRepositoryDb();
    private final UserService userService = new UserServiceImpl();

    @Override
    public List<UserData> statMoreThan5Hiking() {
        List<User> userList = userHikingRepository.statMoreThan5Hiking();
        List<UserData> resultList = new ArrayList<>();
        for (User object : userList) {
            resultList.add(userService.convertToUserData(object));
        }
        return resultList;
    }

    @Override
    public List<UserData> statAgeBetween15And20() {
        List<User> userList = userRepository.statAgeBetween15And20();
        List<UserData> resultList = new ArrayList<>();
        for (User object : userList) {
            resultList.add(userService.convertToUserData(object));
        }
        return resultList;
    }

    @Override
    public List<UserData> statBornInJuly() {
        List<User> userList = userRepository.statBornInJuly();
        List<UserData> resultList = new ArrayList<>();
        for (User object : userList) {
            resultList.add(userService.convertToUserData(object));
        }
        return resultList;
    }

    @Override
    public List<UserData> statCompletedMediumHike() {
        List<User> userList = userHikingRepository.statCompletedMediumHike();
        List<UserData> resultList = new ArrayList<>();
        for (User object : userList) {
            resultList.add(userService.convertToUserData(object));
        }
        return resultList;
    }
}
