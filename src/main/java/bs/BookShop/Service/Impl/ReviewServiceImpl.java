package bs.BookShop.Service.Impl;

import bs.BookShop.Model.Review;
import bs.BookShop.Model.User;
import bs.BookShop.Model.exceptions.ReviewNotFoundException;
import bs.BookShop.Repository.ReviewRepository;
import bs.BookShop.Service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> listReviews() {
        return this.reviewRepository.findAll();
    }

    @Override
    public Review findById(Long id) {
        return this.reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @Override
    public Review create(User user, Long bookId, Integer rating, String description) {
        Review review = new Review(user,bookId,rating,description);
        return this.reviewRepository.save(review);
    }

    @Override
    public Review update(Long reviewId, User user, Long bookId, Integer rating, String description) {
        Review review = this.reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(reviewId));
        review.setUser(user);
        review.setBookId(bookId);
        review.setRating(rating);
        review.setReviewDescription(description);
        return this.reviewRepository.save(review);
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
