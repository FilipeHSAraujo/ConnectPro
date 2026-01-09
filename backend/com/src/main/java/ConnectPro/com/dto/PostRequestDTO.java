package ConnectPro.com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostRequestDTO(

        @NotBlank
        @Size(max = 120)
        String title,

        @NotBlank
        @Size(max = 5000)
        String content,

        @NotNull
        Long authorId

) {}
