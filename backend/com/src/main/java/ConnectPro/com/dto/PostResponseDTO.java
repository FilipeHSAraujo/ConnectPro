package ConnectPro.com.dto;

import java.time.LocalDateTime;

public record PostResponseDTO(
        Long id,
        String title,
        String content,
        Long authorId,
        LocalDateTime createdAt
) {}
