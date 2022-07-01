package ru.practicum.shareit.user.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.DuplicateEmailException;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.util.AbstractInMemoryDao;

@Repository
public class UserDaoImpl extends AbstractInMemoryDao<User> implements UserDao {
    @Override
    public User save(User user) {
        boolean isContainsDup = getAll().stream()
                .anyMatch(u -> u.getEmail().equals(user.getEmail()) && !u.getId().equals(user.getId()));

        if (isContainsDup) throw new DuplicateEmailException();

        return super.save(user);
    }
}
