package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.CreateOrUpdateUserDto;
import ru.practicum.shareit.user.dto.PublicUserDto;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping
    List<PublicUserDto> getAll() {
        return userService.getAll().stream()
                .map(u -> modelMapper.map(u, PublicUserDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    PublicUserDto get(@PathVariable long id) {
        return modelMapper.map(userService.get(id), PublicUserDto.class);
    }

    @PostMapping
    PublicUserDto create(@Valid @RequestBody CreateOrUpdateUserDto createUserDto) {
        User user = userService.save(modelMapper.map(createUserDto, User.class));
        return modelMapper.map(user, PublicUserDto.class);
    }

    @PatchMapping("{id}")
    PublicUserDto update(@PathVariable long id, @Valid @RequestBody CreateOrUpdateUserDto userDto) {
        User user = userService.get(id);
        modelMapper.map(userDto, user);
        user = userService.save(user);
        return modelMapper.map(user, PublicUserDto.class);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        userService.delete(id);
    }
}
