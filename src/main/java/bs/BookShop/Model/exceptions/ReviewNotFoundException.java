package bs.BookShop.Model.exceptions;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(Long reviewId) {
        super(String.format("Review with id %d does not exist.", reviewId));
    }
}