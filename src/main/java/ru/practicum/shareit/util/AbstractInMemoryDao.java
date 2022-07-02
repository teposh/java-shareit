package ru.practicum.shareit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractInMemoryDao<V extends AbstractBaseEntity> implements AbstractDao<V> {
    final ConcurrentMap<Long, V> map = new ConcurrentHashMap<>();
    final AtomicLong counter = new AtomicLong();

    @Override
    public List<V> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public V get(Long id) {
        return map.get(id);
    }

    @Override
    public V save(V obj) {
        if (obj.isNew()) {
            obj.setId(counter.incrementAndGet());
            map.put(obj.getId(), obj);
            return obj;
        }

        return map.computeIfPresent(obj.getId(), (id, oldVal) -> obj);
    }

    @Override
    public boolean delete(Long id) {
        return map.remove(id) != null;
    }
}
