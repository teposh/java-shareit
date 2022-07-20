package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.PublicCommentDto;

import java.util.List;

public interface ItemService {
    List<Item> getAll(long userId);

    Item get(long id);

    Item save(Item item);

    void delete(long id);

    List<Item> searchBy(String text);

    Comment saveComment(Comment comment);

    List<PublicCommentDto> getComments(long itemId);
}
