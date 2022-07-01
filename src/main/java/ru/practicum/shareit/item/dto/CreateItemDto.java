package ru.practicum.shareit.item.dto;

import lombok.Data;
import ru.practicum.shareit.requests.ItemRequest;

@Data
public class CreateItemDto {
    Long id;

    String name;

    String description;

    Boolean available;

    ItemRequest request;
}
