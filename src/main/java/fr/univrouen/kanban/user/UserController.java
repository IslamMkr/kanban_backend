package fr.univrouen.kanban.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "//localhost:8080/")
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

    @GetMapping(path = "/username/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        System.out.println("Controller : " + username);
        return userService.getUserByUsername(username);
    }

    @PostMapping(path = "save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping(path = "/delete/{uid}")
    public String deleteUser(@PathVariable("uid") Long uid) {
        return userService.deleteUser(uid);
    }

}
