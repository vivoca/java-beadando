package org.example.repository;

import org.example.exception.EntityNotfoundException;

import java.util.UUID;

public class MunkagepRepositoryMemory extends GeneralRepositoryMemory<Munkagep> implements MunkagepRepository{

    @Override
    public Munkagep save(final Munkagep object) {
        Munkagep found = null;
        if (object.getId() != null && object.getId().length() > 0) { // update
//            Feladat feladat1 = feladatList.stream()
//                    .filter( f -> f.getId().equals(object.getId()))
//                    .findFirst()
//                    .get();
//            feladat1.setNev(object.getNev());
//            feladat1.setFeladatTipus(object.getFeladatTipus());

            for (Munkagep item : objectList) {
                if (item.getId().equals(object.getId())) {
                    item.setNev(object.getNev());
                    item.setFeladatTipus(object.getFeladatTipus());
                    item.setFeladatList(item.getFeladatList());
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

}
