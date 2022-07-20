package ru.practicum.shareit.requests;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.ConfigVars;
import ru.practicum.shareit.exceptions.NotValidOwnerException;
import ru.practicum.shareit.requests.dto.CreateOrUpdateItemRequestDto;
import ru.practicum.shareit.requests.dto.PublicItemRequestDto;
import ru.practicum.shareit.user.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/requests")
public class ItemRequestController {
    private final ItemRequestService itemRequestService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping
    List<PublicItemRequestDto> getAll() {
        return itemRequestService.getAll().stream()
                .map(i -> modelMapper.map(i, PublicItemRequestDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    PublicItemRequestDto get(@PathVariable long id) {
        return modelMapper.map(itemRequestService.get(id), PublicItemRequestDto.class);
    }

    @PostMapping
    PublicItemRequestDto create(@Valid @RequestBody CreateOrUpdateItemRequestDto itemRequestDto,
                                @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        ItemRequest itemRequest = modelMapper.map(itemRequestDto, ItemRequest.class);
        itemRequest.setRequestor(userService.get(userId));
        return modelMapper.map(
                itemRequestService.save(itemRequest), PublicItemRequestDto.class
        );
    }

    @PatchMapping("{id}")
    PublicItemRequestDto update(@PathVariable long id,
                                @Valid @RequestBody CreateOrUpdateItemRequestDto itemRequestDto,
                                @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        ItemRequest itemRequest = itemRequestService.get(id);
        if (itemRequest.getRequestor().getId() != userId) throw new NotValidOwnerException();
        modelMapper.map(itemRequestDto, itemRequest);
        return modelMapper.map(
                itemRequestService.save(itemRequest), PublicItemRequestDto.class
        );
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        itemRequestService.delete(id);
    }
}
