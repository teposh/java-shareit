package ru.practicum.shareit.booking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.dto.PublicItemDto;
import ru.practicum.shareit.user.dto.PublicUserDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PublicBookingDto {
    Long id;

    LocalDateTime start;

    LocalDateTime end;

    PublicItemDto item;

    PublicUserDto booker;

    String status;
}
