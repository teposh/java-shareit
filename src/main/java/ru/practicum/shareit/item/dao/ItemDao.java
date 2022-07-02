package ru.practicum.shareit.item.dao;

import ru.practicum.shareit.item.Item;
import ru.practicum.shareit.util.AbstractDao;

import java.util.List;

public interface ItemDao extends AbstractDao<Item> {
    List<Item> getAll(Long userId);

    List<Item> searchBy(String text);
}
