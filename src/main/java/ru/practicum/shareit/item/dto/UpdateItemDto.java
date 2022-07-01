package ru.practicum.shareit.item.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UpdateItemDto {
    @NotNull @Min(0) Long id;

    String name;

    String description;

    Boolean available;
}
