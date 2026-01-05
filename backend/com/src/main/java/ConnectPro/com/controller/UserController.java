package ConnectPro.com.controller;
import ConnectPro.com.service.PostService;

import ConnectPro.com.dto.PostRequestDTO;
import ConnectPro.com.dto.PostResponseDTO;
import ConnectPro.com.model.User;
import ConnectPro.com.service.UserService;
import ConnectPro.com.service.impl.PostServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public UserController(UserService userService,PostService postService){
        this.userService = userService;this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id){
        return userService.findUserById(id).orElseThrow(() ->  new RuntimeException("User not found"));
    }

    @GetMapping("/username/{username}")
    public User findUserByUsername(@PathVariable String username){
        return userService.findUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping
    public List <User> findAllUsers() {
        return userService.findAllUsers();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PostMapping
    public PostResponseDTO createPost(
            @Valid @RequestBody PostRequestDTO dto,
            Authentication authentication
    ) {
        return postService.create(dto, authentication.getName());
    }

}