package ru.practicum.shareit.item;

import java.util.List;

public interface ItemService {
    List<Item> getAll(Long userId);

    Item get(Long id);

    Item create(Item item);

    Item update(Item item);

    void delete(Long id);

    List<Item> searchBy(String text);
}
