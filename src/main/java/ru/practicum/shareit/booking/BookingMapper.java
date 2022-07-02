package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.BookingDto;

public class BookingMapper {
    public static Booking toBooking(BookingDto bookingDto, long userId) {
        return Booking.builder()
                .id(bookingDto.getId())
                .start(bookingDto.getStart())
                .end(bookingDto.getEnd())
                .itemId(bookingDto.getItemId())
                .bookerId(userId)
                .status(BookingStatus.valueOf(bookingDto.getStatus()))
                .build();
    }

    public static BookingDto toBookingDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .start(booking.getStart())
                .end(booking.getEnd())
                .itemId(booking.getItemId())
                .status(booking.getStatus().toString())
                .build();
    }
}
