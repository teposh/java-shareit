package ru.practicum.shareit.requests.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateOrUpdateItemRequestDto {
    @NotBlank String description;
}
