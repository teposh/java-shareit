package ru.practicum.shareit.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserDto {
    @NotNull @Min(0) Long id;

    String name;

    @Email String email;
}
