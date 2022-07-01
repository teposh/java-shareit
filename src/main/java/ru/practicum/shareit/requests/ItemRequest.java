package ru.practicum.shareit.requests;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.util.AbstractBaseEntity;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class ItemRequest implements AbstractBaseEntity {
    Long id;

    String description;

    Long requestorId;

    LocalDate created;
}
