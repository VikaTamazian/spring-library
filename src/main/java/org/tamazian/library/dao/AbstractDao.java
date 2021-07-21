package org.tamazian.library.dao;

import java.util.List;

public interface AbstractDao<K, E> {

    void delete(K value);

    void save(E entity);

    void update(K value, E entity);

    E findById(K value);

    List<E> findAll();

}
