package ru.practicum.shareit.requests;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.ConfigVars;
import ru.practicum.shareit.exceptions.NotValidOwnerException;
import ru.practicum.shareit.requests.dto.ItemRequestDto;
import ru.practicum.shareit.user.UserService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/requests")
public class ItemRequestController {
    private final ItemRequestService itemRequestService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping
    List<ItemRequest> getAll() {
        return itemRequestService.getAll();
    }

    @GetMapping("{id}")
    ItemRequest get(@PathVariable long id) {
        return itemRequestService.get(id);
    }

    @PostMapping
    ItemRequest create(@Valid @RequestBody ItemRequestDto itemRequestDto,
                       @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        ItemRequest itemRequest = modelMapper.map(itemRequestDto, ItemRequest.class);
        itemRequest.setRequestor(userService.get(userId));
        return itemRequestService.save(itemRequest);
    }

    @PatchMapping("{id}")
    ItemRequest update(@PathVariable long id,
                       @Valid @RequestBody ItemRequestDto itemRequestDto,
                       @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        ItemRequest itemRequest = itemRequestService.get(id);
        if (itemRequest.getRequestor().getId() != userId) throw new NotValidOwnerException();
        modelMapper.map(itemRequestDto, itemRequest);
        return itemRequestService.save(itemRequest);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        itemRequestService.delete(id);
    }
}
