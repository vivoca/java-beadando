package hu.egyudv.beadando.repository.db;

import hu.egyudv.beadando.repository.UserHikingRepository;
import hu.egyudv.beadando.repository.db.entity.Hiking;
import hu.egyudv.beadando.repository.db.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserHikingRepositoryDb implements UserHikingRepository {
    @Override
    public List<Hiking> getHikingListByUser(long userId) {
        return new ArrayList<>();
    }

    @Override
    public List<User> getUserListByHiking(long hikingId) {
        return new ArrayList<>();
    }

    @Override
    public void save(long userId, long hikingId) {

    }

    @Override
    public void delete(long userId, long hikingId) {

    }

    @Override
    public void deleteAllByUser(long userId) {

    }
}
