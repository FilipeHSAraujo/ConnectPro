package ConnectPro.com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record PostResponseDTO(
        @NotNull Long id,
        @NotBlank String content,
        @NotNull Long authorId,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {}
