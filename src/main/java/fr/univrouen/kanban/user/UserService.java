package fr.univrouen.kanban.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final String EMAIL_USED = "{ \"error\": \"email already used!\" }";
    private final String USER_SAVED = "{ \"success\": \"user saved successfully.\" }";
    private final String USER_DELETED = "{ \"success\": \"user deleted successfully.\" }";
    private final String USER_NOT_FOUND = "{ \"error\": \"user not found.\" }";

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public String saveUser(User user) {
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());

        if (!optionalUser.isPresent()) {
            userRepository.save(user);

            return USER_SAVED;
        }

        return EMAIL_USED;
    }

    public User getUserByUid(Long uid) {
        Optional<User> optionalUser = userRepository.findById(uid);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        return null;
    }

    public String deleteUser(Long uid) {
        boolean userExists = userRepository.existsById(uid);

        if (userExists) {
            userRepository.deleteById(uid);

            return USER_DELETED;
        }

        return USER_NOT_FOUND;
    }
}
