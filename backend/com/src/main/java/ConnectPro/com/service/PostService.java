package ConnectPro.com.service;

import ConnectPro.com.dto.PostRequestDTO;
import ConnectPro.com.dto.PostResponseDTO;
import ConnectPro.com.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    PostResponseDTO create(PostRequestDTO dto, String username);

    Optional<Post> findPostById(Long id);

    List<Post> findAllPosts();

    List<Post> findPostByUser(Long userId);

    void deletePost(Long id);

}
