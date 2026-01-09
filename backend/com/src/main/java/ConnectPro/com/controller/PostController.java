package ConnectPro.com.controller;

<<<<<<< HEAD
public class PostController {
=======
import ConnectPro.com.model.Post;
import ConnectPro.com.model.User;
import ConnectPro.com.repository.UserRepository;
import ConnectPro.com.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;

    public PostController(PostService postService, UserRepository userRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
    }

    // Create a post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post postRequest) {
        User user = userRepository.findById(postRequest.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        postRequest.setAuthor(user);
        return postService.createPost(postRequest);
    }

    // Get a post by ID
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    // Get all posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // Update a post
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post postRequest) {
        return postService.updatePost(id, postRequest.getContent());
    }

    // Delete a post
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
>>>>>>> main
}
