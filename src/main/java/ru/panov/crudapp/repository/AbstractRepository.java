package ru.panov.crudapp.repository;

import ru.panov.crudapp.model.AbstractBaseEntity;

import java.util.List;

public interface AbstractRepository<T extends AbstractBaseEntity, ID> {
    T getById(ID id);

    List<T> getAll();

    T save(T t);

    T update(T t);

    void deleteById(ID id);
}