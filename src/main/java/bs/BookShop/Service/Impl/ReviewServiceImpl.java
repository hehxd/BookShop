package bs.BookShop.Service.Impl;

import bs.BookShop.Model.Review;
import bs.BookShop.Model.User;
import bs.BookShop.Model.dto.ReviewDto;
import bs.BookShop.Model.exceptions.ReviewNotFoundException;
import bs.BookShop.Repository.ReviewRepository;
import bs.BookShop.Service.ReviewService;
import bs.BookShop.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;


    public ReviewServiceImpl(ReviewRepository reviewRepository, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
    }

    @Override
    public List<Review> listReviews() {
        return this.reviewRepository.findAll();
    }

    @Override
    public Review findById(Long id) {
        return this.reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }

    public Optional<Review> create(ReviewDto reviewDto) {
        User user = this.userService.findById(reviewDto.getUser());
        Review review = new Review(user, reviewDto.getBookId(), reviewDto.getRating(), reviewDto.getReviewDescription());
        return Optional.of(this.reviewRepository.save(review));
    }

    public Optional<Review> update(Long reviewId, ReviewDto reviewDto) {
        Review review = this.reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(reviewId));
        User user = this.userService.findById(reviewDto.getUser());
        review.setUser(user);
        review.setBookId(reviewDto.getBookId());
        review.setRating(reviewDto.getRating());
        review.setReviewDescription(reviewDto.getReviewDescription());
        return Optional.of(this.reviewRepository.save(review));
    }

    @Override
    public Review delete(Long id) {
        Review review = this.reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
        this.reviewRepository.deleteById(id);
        return review;
    }

    @Override
    public List<Review> getReviewsByBookId(Long bookId) {
        return this.reviewRepository.findByBookId(bookId);
    }
}
