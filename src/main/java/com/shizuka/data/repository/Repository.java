package com.shizuka.data.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    T insert(T entity);
    Optional<T> findById(int id);
    List<T> getAll();
    boolean update(int id, T updatedEntity);
    boolean delete(int id);
}
