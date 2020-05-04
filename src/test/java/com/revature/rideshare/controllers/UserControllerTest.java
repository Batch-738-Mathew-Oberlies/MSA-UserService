package com.revature.rideshare.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rideshare.models.User;
import com.revature.rideshare.models.UserDTO;
import com.revature.rideshare.services.DistanceService;
import com.revature.rideshare.services.UserService;
import com.revature.rideshare.utilities.MockObjects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private DistanceService distanceService;

    @Test
    public void testGettingUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        when(userService.getUsers()).thenReturn(users);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGettingUserById() throws Exception {
        User user = MockObjects.getAdonis();

        when(userService.getUserById(1)).thenReturn(java.util.Optional.of(user));

        mockMvc.perform(get("/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1));
    }

    @Test
    public void testGettingUserByUsername() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(MockObjects.getAdonis());

        when(userService.getUserByUsername("userName")).thenReturn(users);

        mockMvc.perform(get("/?username=userName"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userName").value("userName"));
    }

    @Test
    public void testGettingUserByRole() throws Exception {
        List<User> users = new ArrayList<>();
        User user = MockObjects.getAdonis();
        users.add(user);

        when(userService.getUserByRole(true)).thenReturn(users);

        mockMvc.perform(get("/?is-driver=true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].driver").value("true"));
    }

    @Test
    public void testGettingUserByRoleAndLocation() throws Exception {
        List<User> users = new ArrayList<>();
        User user = MockObjects.getAdonis();
        users.add(user);

        when(userService.getUserByRoleAndLocation(true, "location")).thenReturn(users);

        mockMvc.perform(get("/?is-driver=true&location=location"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].driver").value("true"));
    }

    @Test
    public void testAddingUser() throws Exception {
        User user = MockObjects.getAdonis();
        UserDTO dto = new UserDTO(user);

        when(userService.addUser(new User(dto))).thenReturn(user);

        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value("userName"));
    }

    @Test
    public void testUpdatingUser() throws Exception {
        User user = MockObjects.getAdonis();

        when(userService.updateUser(user)).thenReturn(user);

        mockMvc.perform(put("/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new UserDTO(user))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("userName"));
    }

    @Test
    public void testDeletingUser() throws Exception {
        User user = MockObjects.getAdonis();
        String returnedStr = "User with id: " + user.getUserId() + " was deleted.";

        when(userService.deleteUserById(1)).thenReturn(returnedStr);

        mockMvc.perform(delete("/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(returnedStr));
    }
}
