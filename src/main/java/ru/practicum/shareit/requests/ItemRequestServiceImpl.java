package ru.practicum.shareit.requests;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.requests.dao.ItemRequestDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {
    private final ItemRequestDao itemRequestDao;

    @Override
    public List<ItemRequest> getAll() {
        return itemRequestDao.getAll();
    }

    @Override
    public ItemRequest get(Long id) {
        return itemRequestDao.get(id);
    }

    @Override
    public ItemRequest add(ItemRequest itemRequest) {
        return itemRequestDao.save(itemRequest);
    }

    @Override
    public ItemRequest update(ItemRequest itemRequest) {
        return itemRequestDao.save(itemRequest);
    }

    @Override
    public void delete(Long id) {
        itemRequestDao.delete(id);
    }
}
