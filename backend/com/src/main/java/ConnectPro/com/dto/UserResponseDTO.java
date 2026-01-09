package ConnectPro.com.dto;

import ConnectPro.com.model.UserType;
import java.time.LocalDateTime;

public record UserResponseDTO(

        Long id,
        UserType userType,
        String username,
        String email,
        LocalDateTime createdAt

) {}

