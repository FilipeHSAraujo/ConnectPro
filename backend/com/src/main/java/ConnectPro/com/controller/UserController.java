package ConnectPro.com.controller;

import ConnectPro.com.dto.UserRequestDTO;
import ConnectPro.com.dto.UserResponseDTO;
import ConnectPro.com.model.CompanyUser;
import ConnectPro.com.model.IndividualUser;
import ConnectPro.com.model.User;
import ConnectPro.com.model.UserType;
import ConnectPro.com.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO registerUser(@RequestBody @Valid UserRequestDTO dto) {

        User user = switch (dto.userType()) {
            case ADMIN, FREELANCER -> new IndividualUser();
            case CLIENT -> new CompanyUser();
        };


        user.setUserType(dto.userType());
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());

        User saved = userService.registerUser(user);

        return new UserResponseDTO(
                saved.getId(),
                saved.getUserType(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getCreatedAt()
        );
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable Long id) {

        User user = userService.findUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponseDTO(
                user.getId(),
                user.getUserType(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
