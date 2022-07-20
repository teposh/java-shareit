package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.ConfigVars;
import ru.practicum.shareit.booking.BookingService;
import ru.practicum.shareit.exceptions.HasNotBookingsException;
import ru.practicum.shareit.exceptions.NotValidOwnerException;
import ru.practicum.shareit.item.dto.*;
import ru.practicum.shareit.user.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final BookingService bookingService;

    private final ItemService itemService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping
    List<OwnerItemDto> getAll(@RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        return itemService.getAll(userId).stream().map(item -> {
            OwnerItemDto ownerItemDto = modelMapper.map(item, OwnerItemDto.class);
            ownerItemDto.setLastBooking(bookingService.getLastByItemId(item.getId()));
            ownerItemDto.setNextBooking(bookingService.getNextByItemId(item.getId()));
            ownerItemDto.setComments(itemService.getComments(item.getId()));
            return ownerItemDto;
        }).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    OwnerItemDto get(@PathVariable long id, @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        Item item = itemService.get(id);
        OwnerItemDto ownerItemDto = modelMapper.map(item, OwnerItemDto.class);
        ownerItemDto.setComments(itemService.getComments(id));
        if (item.getOwner().getId() == userId) {
            ownerItemDto.setLastBooking(bookingService.getLastByItemId(id));
            ownerItemDto.setNextBooking(bookingService.getNextByItemId(id));
        }
        return ownerItemDto;
    }

    @PostMapping
    PublicItemDto create(@Valid @RequestBody CreateItemDto createItemDto,
                         @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        Item item = modelMapper.map(createItemDto, Item.class);
        item.setOwner(userService.get(userId));
        return modelMapper.map(
                itemService.save(item), PublicItemDto.class
        );
    }

    @PatchMapping("{id}")
    PublicItemDto update(@PathVariable long id,
                         @Valid @RequestBody UpdateItemDto updateItemDto,
                         @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        Item item = itemService.get(id);
        if (item.getOwner().getId() != userId) throw new NotValidOwnerException();
        modelMapper.map(updateItemDto, item);
        return modelMapper.map(
                itemService.save(item), PublicItemDto.class
        );
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        itemService.delete(id);
    }

    @GetMapping("/search")
    List<PublicItemDto> search(@RequestParam(defaultValue = "") String text) {
        return itemService.searchBy(text).stream()
                .map(i -> modelMapper.map(i, PublicItemDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("{id}/comment")
    PublicCommentDto addComment(@PathVariable long id,
                                @Valid @RequestBody CreateCommentDto createCommentDto,
                                @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        if (!bookingService.isHasBookingsByItemIdAndUserId(id, userId)) {
            throw new HasNotBookingsException();
        }

        Comment comment = modelMapper.map(createCommentDto, Comment.class);
        comment.setItem(itemService.get(id));
        comment.setAuthor(userService.get(userId));

        itemService.saveComment(comment);

        PublicCommentDto publicCommentDto = modelMapper.map(comment, PublicCommentDto.class);
        publicCommentDto.setAuthorName(comment.getAuthor().getName());

        return publicCommentDto;
    }
}
