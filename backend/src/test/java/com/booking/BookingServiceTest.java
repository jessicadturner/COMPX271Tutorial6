package com.booking;

import com.booking.model.Booking;
import com.booking.model.Room;
import com.booking.model.User;
import com.booking.repository.BookingRepository;
import com.booking.service.BookingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    @Test
    public void testSuccessfulBooking() {
        BookingRepository bookingRepository = mock(BookingRepository.class);
        BookingService bookingService = new BookingService(bookingRepository);

        Room room = new Room(1L, "Room A", 10);
        User user = new User(1L, "Alice", "alice@example.com");
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 1, 11, 0);

        when(bookingRepository.findConflictingBookings(room.getId(), start, end)).thenReturn(Optional.empty());

        Booking booking = bookingService.bookRoom(user, room, start, end);

        assertNotNull(booking);
        assertEquals(room, booking.getRoom());
        assertEquals(user, booking.getUser());
    }

    @Test
    public void testBookingConflict() {
        BookingRepository bookingRepository = mock(BookingRepository.class);
        BookingService bookingService = new BookingService(bookingRepository);

        Room room = new Room(1L, "Room A", 10);
        User user = new User(1L, "Alice", "alice@example.com");
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 1, 11, 0);

        when(bookingRepository.findConflictingBookings(room.getId(), start, end)).thenReturn(Optional.of(new Booking()));

        assertThrows(IllegalStateException.class, () -> {
            bookingService.bookRoom(user, room, start, end);
        });
    }
}