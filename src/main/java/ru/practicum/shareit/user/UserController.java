package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.CreateUserDto;
import ru.practicum.shareit.user.dto.UpdateUserDto;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    User get(@PathVariable Long id) {
        return userService.get(id);
    }

    @PostMapping
    User create(@Valid @RequestBody CreateUserDto createUserDto) {
        return userService.add(UserMapper.toUser(createUserDto));
    }

    @PutMapping
    User update(@Valid @RequestBody UpdateUserDto updateUserDto) {
        User currentUser = userService.get(updateUserDto.getId());
        User updatedUser = UserMapper.map(updateUserDto, currentUser);
        return userService.update(updatedUser);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
