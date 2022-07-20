package ru.practicum.shareit.requests;

import java.util.List;

public interface ItemRequestService {
    List<ItemRequest> getAll();

    ItemRequest get(long id);

    ItemRequest save(ItemRequest itemRequest);

    void delete(long id);
}
