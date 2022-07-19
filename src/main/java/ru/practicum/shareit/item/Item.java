package ru.practicum.shareit.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank @Size(max = 255) String name;

    @NotBlank @Size(max = 4000) String description;

    boolean available;

    @ManyToOne
    User owner;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    List<Comment> comments;
}
