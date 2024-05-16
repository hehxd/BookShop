package bs.BookShop.Service;

import bs.BookShop.Model.Review;
import bs.BookShop.Model.User;

import java.util.List;

public interface ReviewService {

    List<Review> listReviews();
    Review findById(Long id);
    Review create(User user, Long bookId, Integer rating, String description);
    Review update(Long reviewId, User user, Long bookId, Integer rating, String description);
    Review delete(Long id);

    List<Review> getReviewsByBookId(Long bookId);



}
