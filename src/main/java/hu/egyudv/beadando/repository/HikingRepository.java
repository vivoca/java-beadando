package hu.egyudv.beadando.repository;


import hu.egyudv.beadando.repository.entity.Hiking;
import hu.egyudv.beadando.repository.entity.User;

import java.util.List;

public interface HikingRepository {
    List<Hiking> all();
    Hiking save(Hiking object);
    void delete(String id);
    Hiking get(String id);
    List<User> getUserCompletedList(String id);

}
