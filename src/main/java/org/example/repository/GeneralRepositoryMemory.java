package org.example.repository;

import org.example.exception.EntityNotfoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GeneralRepositoryMemory<T extends CompanyItem> implements GeneralRepository<T> {

    protected List<T> objectList = new ArrayList<>();

    @Override
    public List<T> all() {
        return objectList.stream().collect(Collectors.toList());
    }

    @Override
    public T save(final T object) {
        T found = null;
        if (object.getId() != null && object.getId().length() > 0) { // update
//            Feladat feladat1 = feladatList.stream()
//                    .filter( f -> f.getId().equals(object.getId()))
//                    .findFirst()
//                    .get();
//            feladat1.setNev(object.getNev());
//            feladat1.setFeladatTipus(object.getFeladatTipus());

            for (T item : objectList) {
                if (item.getId().equals(object.getId())) {
                    item.setNev(object.getNev());
                    item.setFeladatTipus(object.getFeladatTipus());
                    found = item;
                }
            }
            if (found == null) {
                throw new EntityNotfoundException("No such task " + object.getId());
            }
        } else { // insert
            object.setId(UUID.randomUUID().toString());
            objectList.add(object);
            found = object;
        }

        return found;
    }

    @Override
    public void delete(String id) {
        objectList = objectList.stream().filter(feladat -> !feladat.getId().equals(id)).collect(Collectors.toList());
    }

    @Override
    public T get(String id) {
        T result = objectList.stream()
                .filter( f -> f.getId().equals(id))
                .findFirst()
                .get();
        if (result == null) {
            throw new EntityNotfoundException("No such task " + id);
        }
        return result;
    }
}
