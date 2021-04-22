package org.example.model;

import lombok.RequiredArgsConstructor;
import org.example.repository.Feladat;
import org.example.repository.FeladatRepository;

import java.util.List;

@RequiredArgsConstructor
public class FeladatServiceImpl implements FeladatService {

    private final FeladatRepository feladatRepository;

    @Override
    public List<Feladat> all() {
        return feladatRepository.all();
    }

    @Override
    public Feladat save(Feladat object) {
        return feladatRepository.save(object);
    }

    @Override
    public void delete(String id) {
        feladatRepository.delete(id);
    }

    @Override
    public Feladat get(String id) {
        return feladatRepository.get(id);
    }
}
