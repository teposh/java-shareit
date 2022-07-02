package ru.practicum.shareit.booking.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookingDto {
    Long id;

    LocalDate start;

    LocalDate end;

    Long itemId;

    String status;
}
