package ru.practicum.shareit.user;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.util.AbstractBaseEntity;

@Data
@Builder(toBuilder = true)
public class User implements AbstractBaseEntity {
    Long id;

    String name;

    String email;
}
