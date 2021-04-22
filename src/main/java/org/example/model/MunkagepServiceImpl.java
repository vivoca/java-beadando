package org.example.model;

import lombok.RequiredArgsConstructor;
import org.example.exception.EntityNotfoundException;
import org.example.exception.IncompatibilityTaskException;
import org.example.repository.Feladat;
import org.example.repository.FeladatRepository;
import org.example.repository.Munkagep;
import org.example.repository.MunkagepRepository;

import java.util.List;

@RequiredArgsConstructor
public class MunkagepServiceImpl implements MunkagepService {

    private final MunkagepRepository munkagepRepository;
    private final FeladatRepository feladatRepository;

    @Override
    public List<Munkagep> all() {
        return munkagepRepository.all();
    }

    @Override
    public Munkagep save(Munkagep object) {
        return munkagepRepository.save(object);
    }

    @Override
    public void delete(String id) {
        munkagepRepository.delete(id);
    }

    @Override
    public Munkagep get(String id) {
        return munkagepRepository.get(id);
    }

    @Override
    public void feladatFelvetel(String munkagepId, String feladatId) {
        Feladat feladat = feladatRepository.get(feladatId);
        Munkagep munkagep = munkagepRepository.get(munkagepId);

        if (feladat == null) {
            throw new EntityNotfoundException(String.format("no such task %s", feladatId));
        }

        if (munkagep == null) {
            throw new EntityNotfoundException(String.format("no such machine %s", munkagepId));
        }

        if (!feladat.getFeladatTipus().equals(munkagep.getFeladatTipus())) {
            throw new IncompatibilityTaskException();
        }

        munkagep.getFeladatList().add(feladat);

        munkagepRepository.save(munkagep);

    }
}
