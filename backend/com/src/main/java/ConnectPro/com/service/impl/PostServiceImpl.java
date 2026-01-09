package ConnectPro.com.service.impl;

import ConnectPro.com.model.Post;
import ConnectPro.com.model.User;
import ConnectPro.com.repository.PostRepository;
import ConnectPro.com.repository.UserRepository;
import ConnectPro.com.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Post createPost(Post post, Long userId) {
        Objects.requireNonNull(post, "Post cannot be null");
        Objects.requireNonNull(userId, "UserId cannot be null");

        if (post.getContent() == null || post.getContent().isBlank()) {
            throw new IllegalArgumentException("Post content is required");
        }

        User author = userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id: " + userId)
                );

        post.setAuthor(author);
        return postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> findPostByUser(Long userId, Pageable pageable) {
        User author = userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id: " + userId)
                );

        return postRepository.findByAuthorOrderByCreatedAtDesc(author, pageable);
    }

    @Override
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new EntityNotFoundException("Post not found");
        }
        postRepository.deleteById(id);
    }
}
