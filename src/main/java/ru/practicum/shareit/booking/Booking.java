package ru.practicum.shareit.booking;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Booking {
    Long id;

    LocalDate start;

    LocalDate end;

    Long itemId;

    Long bookerId;

    BookingStatus status;
}
