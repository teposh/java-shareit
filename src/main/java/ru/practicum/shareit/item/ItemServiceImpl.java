package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dao.ItemDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemDao itemDao;

    @Override
    public List<Item> getAll(Long userId) {
        return itemDao.getAll(userId);
    }

    @Override
    public Item get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public Item create(Item item) {
        return itemDao.save(item);
    }

    @Override
    public Item update(Item item) {
        return itemDao.save(item);
    }

    @Override
    public void delete(Long id) {
        itemDao.delete(id);
    }

    @Override
    public List<Item> searchBy(String text) {
        return itemDao.searchBy(text);
    }
}
