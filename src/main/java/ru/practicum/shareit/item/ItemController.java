package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.ConfigVars;
import ru.practicum.shareit.exceptions.NotValidOwnerException;
import ru.practicum.shareit.item.dto.CreateItemDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.UpdateItemDto;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    List<Item> getAll(@RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        return itemService.getAll(userId);
    }

    @GetMapping("{id}")
    ItemDto get(@PathVariable Long id) {
        return ItemMapper.toPublicItemDto(itemService.get(id));
    }

    @PostMapping
    Item create(@Valid @RequestBody CreateItemDto createItemDto,
                @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        return itemService.create(ItemMapper.toItem(createItemDto, userId));
    }

    @PutMapping
    Item update(@Valid @RequestBody UpdateItemDto updateItemDto,
                @RequestHeader(ConfigVars.HTTP_USERID_HEADER) long userId) {
        Item currentItem = itemService.get(updateItemDto.getId());
        if (currentItem.getOwnerId() != userId) throw new NotValidOwnerException();
        Item updatedItem = ItemMapper.map(updateItemDto, currentItem);
        return itemService.update(updatedItem);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        itemService.delete(id);
    }

    @GetMapping("/search")
    List<ItemDto> search(@RequestParam(defaultValue = "") String text) {
        return itemService.searchBy(text).stream().map(ItemMapper::toPublicItemDto).collect(Collectors.toList());
    }
}
