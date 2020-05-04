package com.revature.rideshare.services.impl;

import com.revature.rideshare.models.Batch;
import com.revature.rideshare.models.User;
import com.revature.rideshare.repositories.UserRepository;
import com.revature.rideshare.utilities.MockObjects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testGettingUsers() {
        List<User> users = new ArrayList<>();
        users.add(MockObjects.getAdonis());
        users.add(MockObjects.getAdonis());

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(2, userService.getUsers().size());
    }

    @Test
    public void testGettingUserById() {
        User expected = MockObjects.getAdonis();

        when(userRepository.findById(1)).thenReturn(Optional.of(expected));

        Optional<User> actual = userService.getUserById(1);
        if (actual.isPresent()) assertEquals(expected, actual.get());
        else fail();
    }

    @Test
    public void testGettingUserByUsername() {
        List<User> expected = new ArrayList<>();
        expected.add(MockObjects.getAdonis());

        when(userRepository.getUserByUsername("userName")).thenReturn(expected);

        List<User> actual = userService.getUserByUsername("userName");
        assertEquals(expected, actual);
    }

    @Test
    public void testGettingUserByRole() {
        List<User> expected = new ArrayList<>();
        expected.add(MockObjects.getAdonis());

        when(userRepository.getUserByRole(true)).thenReturn(expected);

        List<User> actual = userService.getUserByRole(true);
        assertEquals(expected, actual);
    }

    @Test
    public void testGettingUserByRoleAndLocation() {
        List<User> expected = new ArrayList<>();
        expected.add(MockObjects.getAdonis());

        when(userRepository.getUserByRoleAndLocation(true, "location")).thenReturn(expected);

        List<User> actual = userService.getUserByRoleAndLocation(true, "location");
        assertEquals(expected, actual);
    }

    @Test
    public void testAddingUser() {
        User expected = MockObjects.getAdonis();

        when(userRepository.save(expected)).thenReturn(expected);

        User actual = userService.addUser(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdatingUser() {
        User expected = MockObjects.getAdonis();

        when(userRepository.save(expected)).thenReturn(expected);

        User actual = userService.updateUser(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeletingUser() {
        String expected = "User with id: 1 was deleted.";

        when(userRepository.existsById(1)).thenReturn(true);

        String actual = userService.deleteUserById(1);
        assertEquals(expected, actual);
    }

}
