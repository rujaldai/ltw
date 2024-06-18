package com.mcve.ltw.controller;

import com.mcve.ltw.entity.Users;
import com.mcve.ltw.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class TestController {

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final UserRepository userRepository;

    @GetMapping
    public List<Users> getUsers() {
        Iterator<Users> usersIterable = userRepository.findAll().iterator();
        List<Users> users = new ArrayList<>();
        while (usersIterable.hasNext()) {
            users.add(usersIterable.next());
        }
        return users;
    }

    @PostMapping
    public void addUser(@RequestBody Users user) {
        userRepository.save(user);
    }

}
