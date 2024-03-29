package de.ait.events.repository;

import de.ait.events.modules.Event;

import java.util.List;

public interface CrudRepository <T>{
    void save(T model);
    void deleteById(Long id);
//    void updateById(Long id);
    T findById(Long id);
    List<T> findAll();

    void update(Event eventForUpdate);
}
