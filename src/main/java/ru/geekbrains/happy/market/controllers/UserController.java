package ru.geekbrains.happy.market.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.happy.market.dto.UserDto;
import ru.geekbrains.happy.market.model.User;
import ru.geekbrains.happy.market.repositories.UserRepository;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Data
public class UserController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    //Протестировано постаманом
    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDto userDto) {
        User user = new User(userDto.getUserName(), encoder.encode(userDto.getUserPassword()), userDto.getEmail());
        userRepository.save(user);
    }
}
