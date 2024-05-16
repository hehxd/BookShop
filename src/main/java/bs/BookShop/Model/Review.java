package bs.BookShop.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review_description", length = 1000)
    private String reviewDescription;

    public Review(User user, Long bookId, int rating, String reviewDescription) {
        this.user = user;
        this.bookId = bookId;
        this.rating = rating;
        this.reviewDescription = reviewDescription;
    }

}
