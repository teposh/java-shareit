package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.Item;
import ru.practicum.shareit.util.AbstractInMemoryDao;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ItemDaoImpl extends AbstractInMemoryDao<Item> implements ItemDao {
    @Override
    public List<Item> getAll(Long userId) {
        return getAll().stream().filter(i -> Objects.equals(i.getOwnerId(), userId)).collect(Collectors.toList());
    }

    @Override
    public List<Item> searchBy(String text) {
        return getAll().stream().filter(i -> i.getName().contains(text) || i.getDescription().contains(text))
                .collect(Collectors.toList());
    }
}
