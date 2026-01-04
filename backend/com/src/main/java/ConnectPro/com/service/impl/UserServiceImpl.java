package ConnectPro.com.service.impl;

import ConnectPro.com.model.User;
import ConnectPro.com.model.UserAction;
import ConnectPro.com.model.UserType;
import ConnectPro.com.repository.UserRepository;
import ConnectPro.com.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        Objects.requireNonNull(user, "User cannot be null");

        requireNonBlank(user.getUsername(), "Username is required");
        requireNonBlank(user.getEmail(), "Email is required");
        requireNonBlank(user.getPassword(), "Password is required");
        Objects.requireNonNull(user.getUserType(), "User type is required");

        userRepository.findByUsername(user.getUsername())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Username already exists");
                });

        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Email already exists");
                });

        // ⚠️ Senha SEM criptografia (dev only)
        return userRepository.save(user);
    }

    private static void requireNonBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public boolean canUserPerformAction(Long userId, UserAction action) {
        if (action == null) {
            return false;
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id: " + userId)
                );

        UserType userType = user.getUserType();
        if (userType == null) {
            return false;
        }

        return switch (userType) {
            case ADMIN      -> true;
            case CLIENT     -> action == UserAction.POST_JOB || action == UserAction.VIEW_PROFILE;
            case FREELANCER -> action == UserAction.APPLY_JOB || action == UserAction.VIEW_PROFILE;
        };
    }
}
