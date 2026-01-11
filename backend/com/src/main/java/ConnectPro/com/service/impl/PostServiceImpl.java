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

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id: " + userId)
                );

        post.setAuthor(user);
        return postRepository.save(post);
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
            throw new EntityNotFoundException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }

    @Override
    public Post updatePost(Long id, String content) {
        Objects.requireNonNull(content, "Content cannot be null");

        Post post = postRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Post not found with id: " + id)
                );

        post.setContent(content);
        return postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Post not found with id: " + id)
                );
    }
}
