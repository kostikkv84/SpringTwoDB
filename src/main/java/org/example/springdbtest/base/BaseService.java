package org.example.springdbtest.base;

import java.util.List;

public interface BaseService<T, ID> {
    List<T> getById(ID id);
    List<T> getByParam(String param);
    T save(T entity);
    void deleteByParam(String param);
    Iterable<T> getAll();
    T update(T entity);
    void delete(ID id);
}
