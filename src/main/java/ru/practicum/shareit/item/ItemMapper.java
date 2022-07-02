package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.CreateItemDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.UpdateItemDto;

public class ItemMapper {
    public static ItemDto toPublicItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .build();
    }

    public static Item toItem(CreateItemDto createItemDto, long userId) {
        return Item.builder()
                .name(createItemDto.getName())
                .description(createItemDto.getDescription())
                .available(createItemDto.getAvailable() != null && createItemDto.getAvailable())
                .requestId(createItemDto.getRequest() != null ? createItemDto.getRequest().getId() : null)
                .ownerId(userId)
                .build();
    }

    public static Item map(UpdateItemDto updateItemDto, Item currentItem) {
        Item.ItemBuilder itemBuilder = currentItem.toBuilder();

        if (updateItemDto.getName() != null) {
            itemBuilder.name(updateItemDto.getName());
        }

        if (updateItemDto.getDescription() != null) {
            itemBuilder.description(updateItemDto.getDescription());
        }

        if (updateItemDto.getAvailable() != null) {
            itemBuilder.available(updateItemDto.getAvailable());
        }

        return itemBuilder.build();
    }
}
