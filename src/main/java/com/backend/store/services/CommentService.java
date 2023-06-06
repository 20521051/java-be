package com.backend.store.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.backend.store.models.Comment;
import com.backend.store.models.Product;
import com.backend.store.models.User;
import com.backend.store.repositories.CommentRepository;
import com.backend.store.repositories.ProductRepository;
import com.backend.store.repositories.UserRepository;
import com.backend.store.services.CloudinaryService;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Comment createComment(String id, String content, int rate, String userId) {
        try {
            Comment newComment = new Comment();
            newComment.setContent(content);
            newComment.setRate(rate);
            newComment.setUserId(userId);

            Comment savedComment = commentRepository.save(newComment);

             Optional<Comment> resComment = commentRepository.findById(savedComment.getId())
            //         .select("content rate createdAt user")
            //         .populate("user", "name avatar");

            // if (!resComment.isPresent()) {
            //     return ResponseUtils.handleResFailure(ERROR_COMMENT_NOT_FOUND, HttpStatus.NOT_FOUND);
            // }

            // productRepository.updateComments(dto.getProductId(), savedComment.getId());

            Product product = productRepository.findById(id);

            // if (!product.isPresent()) {
            //     return ResponseUtils.handleResFailure(ERROR_PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND);
            // }

            System.out.println("product.rating: " + product.get().getRating());
            product.get().setRating((product.get().getRating() * (product.get().getComments().size() - 1) + rate) / product.get().getComments().size());
            System.out.println("product.rating: " + product.get().getRating());

            productRepository.save(product.get());

            String userAvatar = CloudinaryService.getImageUrl(userRepository.findAvatarById(resComment.get().getUserId().getUser().getAvatar()));

            resComment.get().getUser().setAvatar(userAvatar);

            Comment responseDTO = new Comment();
            responseDTO.setContent(resComment.get().getContent());
            responseDTO.setRate(resComment.get().getRate());
            responseDTO.setCreatedAt(resComment.get().getCreatedAt());
            responseDTO.setUser(resComment.get().getUser());

            return  responseDTO;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }
}