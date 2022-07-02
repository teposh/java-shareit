package ru.practicum.shareit.util;

import java.util.List;

public interface AbstractDao<V extends AbstractBaseEntity> {
    List<V> getAll();

    V get(Long id);

    V save(V obj);

    boolean delete(Long id);
}
