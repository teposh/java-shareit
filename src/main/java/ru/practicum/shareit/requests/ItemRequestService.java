package ru.practicum.shareit.requests;

import java.util.List;

public interface ItemRequestService {
    List<ItemRequest> getAll();

    ItemRequest get(Long id);

    ItemRequest add(ItemRequest itemRequest);

    ItemRequest update(ItemRequest itemRequest);

    void delete(Long id);
}
