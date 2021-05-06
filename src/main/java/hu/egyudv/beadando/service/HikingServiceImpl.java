package hu.egyudv.beadando.model;

import hu.egyudv.beadando.repository.entity.Hiking;
import hu.egyudv.beadando.repository.HikingRepository;
import lombok.RequiredArgsConstructor;

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
    public void feladatFelvetel(String munkagepId, String feladatId) {
//        Feladat feladat = feladatRepository.get(feladatId);
//        Munkagep munkagep = hikingRepository.get(munkagepId);
//
//        if (feladat == null) {
//            throw new EntityNotfoundException(String.format("no such task %s", feladatId));
//        }
//
//        if (munkagep == null) {
//            throw new EntityNotfoundException(String.format("no such machine %s", munkagepId));
//        }
//
//        if (!feladat.getFeladatTipus().equals(munkagep.getFeladatTipus())) {
//            throw new IncompatibilityTaskException();
//        }
//
//        munkagep.getFeladatList().add(feladat);
//
//        hikingRepository.save(munkagep);

    }
}
