package ru.practicum.shareit.item;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.util.AbstractBaseEntity;

@Data
@Builder(toBuilder = true)
public class Item implements AbstractBaseEntity {
    Long id;

    String name;

    String description;

    Boolean available;

    Long ownerId;

    Long requestId;
}
