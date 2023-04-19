package ua.unblv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.unblv.entity.Post;
import ua.unblv.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserOrderByCreatedDataDesc(User user);
    List<Post> findAllByOrderByCreatedDataDesc();
    Optional<Post> findPostByIdAndUser(Long id, User user);
}
