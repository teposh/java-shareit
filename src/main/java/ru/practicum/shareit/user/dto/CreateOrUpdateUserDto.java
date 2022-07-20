package ru.practicum.shareit.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateUserDto {
    @Size(max = 250) String name;

    @Size(max = 512) @Email String email;
}
