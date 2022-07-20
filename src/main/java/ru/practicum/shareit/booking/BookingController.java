package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.ConfigVars;
import ru.practicum.shareit.booking.dto.CreateBookingDto;
import ru.practicum.shareit.booking.dto.PublicBookingDto;
import ru.practicum.shareit.exceptions.ItemNotAvailableException;
import ru.practicum.shareit.exceptions.NotValidOwnerException;
import ru.practicum.shareit.item.Item;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserService;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/bookings")
public class BookingController {
    private final BookingService bookingService;

    private final ItemService itemService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping("{id}")
    PublicBookingDto get(@PathVariable long id, @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        Booking booking = bookingService.get(id);

        if (!(booking.getBooker().getId() == userId || booking.getItem().getOwner().getId() == userId)) {
            throw new NotValidOwnerException();
        }

        return modelMapper.map(booking, PublicBookingDto.class);
    }

    @PostMapping
    PublicBookingDto create(@Valid @RequestBody CreateBookingDto createBookingDto,
                            @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        Item item = itemService.get(createBookingDto.getItemId());
        User user = userService.get(userId);

        if (!item.isAvailable()) throw new ItemNotAvailableException();

        if (item.getOwner().getId() == userId) {
            throw new NoSuchElementException(); // bad decision but Postman tests want strictly 404
        }

        if (createBookingDto.getEnd().isBefore(createBookingDto.getStart())) {
            throw new ValidationException();
        }

        Booking booking = modelMapper.map(createBookingDto, Booking.class);
        booking.setItem(item);
        booking.setBooker(user);
        booking.setStatus(BookingStatus.WAITING);

        return modelMapper.map(bookingService.save(booking), PublicBookingDto.class);
    }

    @PatchMapping("{id}")
    PublicBookingDto update(@PathVariable long id,
                            @RequestParam boolean approved,
                            @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        Booking booking = bookingService.get(id);

        if (booking.getItem().getOwner().getId() != userId) throw new NotValidOwnerException();
        if (booking.getStatus().equals(BookingStatus.APPROVED)) {
            throw new ItemNotAvailableException(); // user can't change status after them approved booking
        }

        booking.setStatus(approved ? BookingStatus.APPROVED : BookingStatus.REJECTED);
        return modelMapper.map(bookingService.save(booking), PublicBookingDto.class);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        bookingService.delete(id);
    }

    @GetMapping
    List<PublicBookingDto> getAllByCurrentUser(@RequestParam(defaultValue = "ALL") String state,
                                               @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        return toPublicBookingDtos(bookingService.getAllByCurrentUser(userId, state));
    }

    @GetMapping("/owner")
    List<PublicBookingDto> getAllByOwnedItems(@RequestParam(defaultValue = "ALL") String state,
                                              @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        return toPublicBookingDtos(bookingService.getAllByOwnedItems(userId, state));
    }

    private List<PublicBookingDto> toPublicBookingDtos(List<Booking> bookings) {
        return bookings.stream()
                .map(b -> modelMapper.map(b, PublicBookingDto.class))
                .collect(Collectors.toList());
    }
}
