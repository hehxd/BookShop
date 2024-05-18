package bs.BookShop.Service;

import bs.BookShop.Model.Review;
import bs.BookShop.Model.User;
import bs.BookShop.Model.dto.ReviewDto;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<Review> listReviews();
    Review findById(Long id);
    Optional<Review> create(ReviewDto reviewDto);
    Optional<Review> update(Long reviewId, ReviewDto reviewDto);
    Review delete(Long id);

    List<Review> getReviewsByBookId(Long bookId);



}
