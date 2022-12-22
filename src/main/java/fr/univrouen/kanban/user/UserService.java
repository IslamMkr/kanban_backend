package fr.univrouen.kanban.user;

import fr.univrouen.kanban.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found in database");
        }

        User user = optionalUser.get();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<SimpleGrantedAuthority>()
        );
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }

    public User saveUser(User user) {
        Optional<User> userWithEmail = userRepository.findUserByEmail(user.getEmail());
        Optional<User> userWithUsername = userRepository.findUserByUsername(user.getUsername());

        boolean validData = !user.getFirstname().isEmpty() && !user.getLastname().isEmpty() && !user.getPassword().isEmpty();

        if (userWithEmail.isEmpty() && userWithUsername.isEmpty() && validData) {
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            return userRepository.save(user);
        }

        return null;
    }

    public User getUserByUid(Long uid) {
        Optional<User> optionalUser = userRepository.findById(uid);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(null);

            return user;
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

    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            user.setPassword(null);

            return user;
        }

        return null;
    }
}
