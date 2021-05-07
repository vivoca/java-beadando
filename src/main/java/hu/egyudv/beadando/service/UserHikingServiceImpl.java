package hu.egyudv.beadando.service;

import hu.egyudv.beadando.repository.UserHikingRepository;
import hu.egyudv.beadando.repository.UserHikingRepositoryFile;
import hu.egyudv.beadando.repository.entity.Hiking;
import hu.egyudv.beadando.repository.entity.User;

import java.util.List;

public class UserHikingServiceImpl implements UserHikingService{

    private final UserHikingRepository userHikingRepository = new UserHikingRepositoryFile();

    @Override
    public List<Hiking> getHikingListByUser(String userId) {
        return userHikingRepository.getHikingListByUser(userId);
    }

    @Override
    public List<User> getUserListByHiking(String hikingId) {
        return userHikingRepository.getUserListByHiking(hikingId);
    }

    @Override
    public void save(String userId, String hikingId) {
        userHikingRepository.save(userId, hikingId);
    }

    @Override
    public void delete(String userId, String hikingId) {
        userHikingRepository.delete(userId, hikingId);
    }
}
