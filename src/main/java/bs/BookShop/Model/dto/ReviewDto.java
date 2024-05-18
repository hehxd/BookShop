package bs.BookShop.Model.dto;

import bs.BookShop.Model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ReviewDto {
    private Long user;


    private Long bookId;


    private int rating;


    private String reviewDescription;

    public ReviewDto(Long user, Long bookId, int rating, String reviewDescription) {
        this.user = user;
        this.bookId = bookId;
        this.rating = rating;
        this.reviewDescription = reviewDescription;
    }
}
