package ru.job4j.chat.service;


import java.util.List;
import java.util.Optional;

public interface SimpleService<T, ID> {
    T saveOrUpdate(T model);
    List<T> findAll();
    Optional<T> findById(ID id);
    void delete(T model);
}
