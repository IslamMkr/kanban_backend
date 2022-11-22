package fr.univrouen.kanban.user;

import fr.univrouen.kanban.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

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

            return Consts.USER_SAVED;
        }

        return Consts.EMAIL_USED;
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

            return Consts.USER_DELETED;
        }

        return Consts.USER_NOT_FOUND;
    }
}
