package ru.practicum.shareit.requests.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.requests.ItemRequest;
import ru.practicum.shareit.util.AbstractInMemoryDao;

@Repository
public class ItemRequestDaoImpl extends AbstractInMemoryDao<ItemRequest> implements ItemRequestDao {
}
