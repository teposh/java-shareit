package ru.practicum.shareit.booking;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.Item;
import ru.practicum.shareit.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "start_date_time")
    @NotNull LocalDateTime start;

    @Column(name = "end_date_time")
    @NotNull LocalDateTime end;

    @NotNull
    @ManyToOne
    Item item;

    @NotNull
    @ManyToOne
    User booker;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    BookingStatus status;
}
