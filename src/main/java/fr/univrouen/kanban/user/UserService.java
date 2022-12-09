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

@Service @Transactional
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

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());

        if (optionalUser.isEmpty()) {
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            return userRepository.save(user);
        }

        return null;
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
