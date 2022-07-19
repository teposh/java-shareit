package ru.practicum.shareit.user;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User get(long id);

    User save(User user);

    void delete(long id);
}
