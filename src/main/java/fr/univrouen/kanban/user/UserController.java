package fr.univrouen.kanban.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{uid}")
    public User getUserByUid(@PathVariable("uid") Long uid) {
        return userService.getUserByUid(uid);
    }

    @PostMapping(path = "save")
    public String saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping(path = "/delete/{uid}")
    public String deleteUser(@PathVariable("uid") Long uid) {
        return userService.deleteUser(uid);
    }
}
