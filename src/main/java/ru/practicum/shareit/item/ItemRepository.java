package ru.practicum.shareit.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> getAllByOwnerId(long userId);

    List<Item> getAllByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndAvailableIsTrue(String text1, String text2);
}
