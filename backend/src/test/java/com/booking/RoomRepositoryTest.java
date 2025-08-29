package com.booking;

import com.booking.model.Room;
import com.booking.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void testFindByName() {
        Room room = new Room(null, "Conference Room", 20);
        roomRepository.save(room);

        Room found = roomRepository.findByName("Conference Room");
        assertNotNull(found);
        assertEquals(20, found.getCapacity());
    }
}