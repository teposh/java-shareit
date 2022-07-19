package ru.practicum.shareit.booking.dto;

import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CreateBookingDto {
    @FutureOrPresent LocalDateTime start;

    @FutureOrPresent LocalDateTime end;

    @NotNull Long itemId;
}
