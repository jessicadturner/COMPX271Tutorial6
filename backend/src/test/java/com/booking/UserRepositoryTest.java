package com.booking;

import com.booking.model.User;
import com.booking.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        User user = new User(null, "Bob", "bob@example.com");
        userRepository.save(user);

        User found = userRepository.findByEmail("bob@example.com");
        assertNotNull(found);
        assertEquals("Bob", found.getName());
    }
}