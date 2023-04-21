package ua.unblv.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.unblv.entity.User;
import ua.unblv.entity.enums.Role;
import ua.unblv.exceptions.UserExistExceptions;
import ua.unblv.payload.request.SignUp;
import ua.unblv.repository.UserRepository;

@Service
public class UserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(SignUp userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstname());
        user.setLastname(userIn.getLastname());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(Role.ROLE_USER);

        try {
            LOGGER.info("Saving user ", userIn.getEmail());
            return  userRepository.save(user);
        }catch (Exception exception) {
            LOGGER.error("Error during registrations ", exception.getMessage());
            throw new UserExistExceptions("The user already exist");
        }
    }
}
