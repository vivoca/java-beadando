package hu.egyudv.beadando.repository;


import hu.egyudv.beadando.repository.db.entity.Hiking;

import java.util.List;

public interface HikingRepository {
    List<Hiking> all();
    Hiking save(Hiking object);
    void delete(long id);
    Hiking get(long id);

}
