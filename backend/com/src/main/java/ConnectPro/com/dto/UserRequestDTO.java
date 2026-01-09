package ConnectPro.com.dto;

import ConnectPro.com.model.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

    @NotNull
    UserType userType,

    @NotBlank
    String username,

    @Email
    @NotBlank
    String email,

    @Size(min = 6)
    String password

){}
