package ConnectPro.com.service;

import ConnectPro.com.model.Post;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
=======
import ConnectPro.com.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
>>>>>>> main

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

<<<<<<< HEAD
    Page<Post> findAllPosts(Pageable pageable);

    Page<Post> findPostByUser(Long userId, Pageable pageable);

    void deletePost(Long id);
=======
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post updatePost(Long id, String content) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setContent(content);
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);
    }
>>>>>>> main
}

