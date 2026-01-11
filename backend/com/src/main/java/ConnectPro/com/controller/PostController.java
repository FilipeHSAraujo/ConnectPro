package ConnectPro.com.controller;

import ConnectPro.com.dto.PostRequestDTO;
import ConnectPro.com.dto.PostResponseDTO;
import ConnectPro.com.dto.PostMapper;
import ConnectPro.com.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDTO createPost(
            @RequestBody @Valid PostRequestDTO dto
    ) {
        return PostMapper.toDTO(
                postService.createPost(PostMapper.toEntity(dto),dto.authorId())
        );
    }

    // READ ONE
    @GetMapping("/{id}")
    public PostResponseDTO getPostById(@PathVariable Long id) {
        return PostMapper.toDTO(
                postService.getPostById(id)
        );
    }

    // READ ALL
    @GetMapping
    public Page<PostResponseDTO> getAllPosts(Pageable pageable) {
        return postService.findAllPosts(pageable)
                .map(PostMapper::toDTO);
    }

    // UPDATE
    @PutMapping("/{id}")
    public PostResponseDTO updatePost(
            @PathVariable Long id,
            @RequestBody @Valid PostRequestDTO dto
    ) {
        return PostMapper.toDTO(
                postService.updatePost(id, dto.content())
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
