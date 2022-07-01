package ru.practicum.shareit.requests;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.ConfigVars;
import ru.practicum.shareit.exceptions.NotValidOwnerException;
import ru.practicum.shareit.requests.dto.ItemRequestDto;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/requests")
public class ItemRequestController {
    private final ItemRequestService itemRequestService;

    @GetMapping
    List<ItemRequest> getAll() {
        return itemRequestService.getAll();
    }

    @GetMapping("{id}")
    ItemRequest get(@PathVariable Long id) {
        return itemRequestService.get(id);
    }

    @PostMapping
    ItemRequest create(@Valid @RequestBody ItemRequestDto itemRequestDto,
                       @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        return itemRequestService.add(ItemRequestMapper.toItemRequest(itemRequestDto, userId));
    }

    @PutMapping
    ItemRequest update(@Valid @RequestBody ItemRequestDto itemRequestDto,
                       @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        ItemRequest currentItemRequest = itemRequestService.get(itemRequestDto.getId());
        if (currentItemRequest.getRequestorId() != userId) throw new NotValidOwnerException();
        ItemRequest updatedItemRequest = ItemRequestMapper.map(itemRequestDto, currentItemRequest);
        return itemRequestService.update(updatedItemRequest);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        itemRequestService.delete(id);
    }
}
