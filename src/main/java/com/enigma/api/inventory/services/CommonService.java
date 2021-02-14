package com.enigma.api.inventory.services;

import com.enigma.api.inventory.entities.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommonService<T, ID> {
    T save(T entity);

    T removeById(ID id);

    T findById(ID id);

    List<T> findAll();

    public Page<T> findAll(T search, int page, int size, Sort.Direction direction);
}
