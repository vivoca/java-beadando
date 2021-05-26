package hu.egyudv.beadando.repository.db;

import hu.egyudv.beadando.repository.HikingRepository;
import hu.egyudv.beadando.repository.db.entity.Hiking;
import hu.egyudv.beadando.repository.db.entity.User;

import java.util.ArrayList;
import java.util.List;

public class HikingRepositoryDb implements HikingRepository {
    @Override
    public List<Hiking> all() {
        return new ArrayList<>();
    }

    @Override
    public Hiking save(Hiking object) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Hiking get(long id) {
        return null;
    }

    @Override
    public List<User> getUserCompletedList(long id) {
        return new ArrayList<>();
    }
}
