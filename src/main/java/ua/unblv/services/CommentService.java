package ua.unblv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.unblv.dto.CommentDTO;
import ua.unblv.entity.Comment;
import ua.unblv.entity.Post;
import ua.unblv.entity.User;
import ua.unblv.exceptions.PostNotFoundExceptions;
import ua.unblv.repository.CommentRepository;
import ua.unblv.repository.PostRepository;
import ua.unblv.repository.UserRepository;

import java.security.Principal;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Comment saveComment(Long postId, CommentDTO commentDTO, Principal principal) {
        User user = getUserPrincipal(principal);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundExceptions("Post cannot be found for username"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUserId(user.getId());
        comment.setUsername(user.getUsername());
        comment.setMessage(commentDTO.getMessage());

        return  commentRepository.save(comment);
    }

    public List<Comment> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundExceptions("Post cannot be found"));

        List<Comment> comments = commentRepository.findAllByPost(post);

        return comments;
    }

    // deleteComment

    private User getUserPrincipal(Principal principal) {
        String username = principal.getName();
        return  userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username"));
    }
}
