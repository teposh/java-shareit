package ru.practicum.shareit.requests.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PublicItemRequestDto {
    Long id;

    String description;

    LocalDate created;
}
