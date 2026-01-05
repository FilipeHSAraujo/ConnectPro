package ConnectPro.com.service.impl;

import ConnectPro.com.dto.PostRequestDTO;
import ConnectPro.com.dto.PostResponseDTO;
import ConnectPro.com.model.Post;
import ConnectPro.com.model.User;
import ConnectPro.com.repository.PostRepository;
import ConnectPro.com.repository.UserRepository;
import ConnectPro.com.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    @Override
    public PostResponseDTO create(PostRequestDTO dto, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setContent(dto.getContent());
        post.setAuthor(user);

        Post saved = postRepository.save(post);

        return new PostResponseDTO(
                saved.getId(),
                saved.getContent(),
                saved.getAuthor().getId(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );
    }

}
