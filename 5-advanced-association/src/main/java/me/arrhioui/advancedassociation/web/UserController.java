package me.arrhioui.advancedassociation.web;

import lombok.*;
import me.arrhioui.advancedassociation.entity.User;
import me.arrhioui.advancedassociation.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public User getUserByUserName(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @PostMapping
    public User auth(@RequestBody UserInput UserInput) {
        System.out.println("UserInput = " + UserInput);
        return userService.authenticate(UserInput.username, UserInput.password);
    }
}

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class UserInput {
    String username;
    String password;
}
