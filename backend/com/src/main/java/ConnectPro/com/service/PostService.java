package ConnectPro.com.service;

import ConnectPro.com.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostService {

    Post createPost(Post post, Long userId);

    Optional<Post> findPostById(Long id);

    Page<Post> findAllPosts(Pageable pageable);

    Page<Post> findPostByUser(Long userId, Pageable pageable);

    void deletePost(Long id);
}

