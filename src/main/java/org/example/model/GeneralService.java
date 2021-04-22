package org.example.model;

import java.util.List;

public interface GeneralService<T> {

    List<T> all();
    // update | new
    T save(T object);
    void delete(String id);
    T get(String id);

}
