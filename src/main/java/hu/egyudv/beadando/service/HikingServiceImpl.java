package hu.egyudv.beadando.service;

import hu.egyudv.beadando.repository.entity.Hiking;
import hu.egyudv.beadando.repository.HikingRepository;
import hu.egyudv.beadando.repository.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class HikingServiceImpl implements HikingService {

    private final HikingRepository hikingRepository;

    @Override
    public List<Hiking> all() {
        return hikingRepository.all();
    }

    @Override
    public Hiking save(Hiking object) {
        return hikingRepository.save(object);
    }

    @Override
    public void delete(String id) {
        hikingRepository.delete(id);
    }

    @Override
    public Hiking get(String id) {
        return hikingRepository.get(id);
    }


    @Override
    public List<User> getUserCompletedList(String id) {
        return hikingRepository.getUserCompletedList(id);
    }
}
