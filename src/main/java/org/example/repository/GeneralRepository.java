package org.example.repository;

import java.util.List;

public interface GeneralRepository<T> {

    List<T> all();
    // update | new
    T save(T object);
    void delete(String id);
    T get(String id);
}
