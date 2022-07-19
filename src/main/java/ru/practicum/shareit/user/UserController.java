package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping
    List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    User get(@PathVariable long id) {
        return userService.get(id);
    }

    @PostMapping
    User create(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PatchMapping("{id}")
    User update(@PathVariable long id, @Valid @RequestBody UserDto userDto) {
        User user = userService.get(id);
        modelMapper.map(userDto, user);
        return userService.save(user);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        userService.delete(id);
    }
}
