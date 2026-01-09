package ConnectPro.com.repository;

import ConnectPro.com.model.Post;
import ConnectPro.com.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Feed global (paginado)
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    // Posts de um usuário (perfil)
    Page<Post> findByAuthorOrderByCreatedAtDesc(User author, Pageable pageable);
}