package ConnectPro.com.dto;

import ConnectPro.com.dto.PostRequestDTO;
import ConnectPro.com.dto.PostResponseDTO;
import ConnectPro.com.model.Post;

public class PostMapper {

    private PostMapper() {

    }

    public static Post toEntity(PostRequestDTO dto) {
        Post post = new Post();
        post.setContent(dto.content());
        return post;
    }

    public static PostResponseDTO toDTO(Post post) {
        return new PostResponseDTO(
                post.getId(),
                post.getContent(),
                post.getAuthor().getId(),
                post.getCreatedAt()
        );
    }
}
