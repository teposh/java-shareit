package ru.practicum.shareit.requests;

import ru.practicum.shareit.requests.dto.ItemRequestDto;

import java.time.LocalDate;

public class ItemRequestMapper {
    public static ItemRequest toItemRequest(ItemRequestDto itemRequestDto, Long userId) {
        return ItemRequest.builder()
                .description(itemRequestDto.getDescription())
                .requestorId(userId)
                .created(LocalDate.now())
                .build();
    }

    public static ItemRequest map(ItemRequestDto itemRequestDto, ItemRequest currentItemRequest) {
        ItemRequest.ItemRequestBuilder builder = currentItemRequest.toBuilder();

        if (itemRequestDto.getDescription() != null) builder.description(itemRequestDto.getDescription());

        return builder.build();
    }
}
