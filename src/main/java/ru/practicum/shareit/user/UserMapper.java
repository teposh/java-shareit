package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.CreateUserDto;
import ru.practicum.shareit.user.dto.UpdateUserDto;

public class UserMapper {
    public static User toUser(CreateUserDto createUserDto) {
        return User.builder()
                .id(createUserDto.getId())
                .name(createUserDto.getName())
                .email(createUserDto.getEmail())
                .build();
    }

    public static User map(UpdateUserDto updateUserDto, User user) {
        User.UserBuilder userBuilder = user.toBuilder();

        if (updateUserDto.getName() != null) {
            userBuilder.name(updateUserDto.getName());
        }

        if (updateUserDto.getEmail() != null) {
            userBuilder.email(updateUserDto.getEmail());
        }

        return userBuilder.build();
    }
}
