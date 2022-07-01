package ru.practicum.shareit.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AbstractBaseEntity {
    Long getId();

    void setId(Long id);

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }
}
