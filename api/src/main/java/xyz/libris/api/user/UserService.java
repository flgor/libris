package xyz.libris.api.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void create(String email, String fullName, String password) {
        User user = new User();

        user.setEmail(email);
        user.setFullName(fullName);
        user.setUniqueId(RandomStringUtils.randomAlphanumeric(15));

        user.setPassword(bCryptPasswordEncoder.encode(password));

        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public long count() {
        return userRepository.count();
    }
}
