package bs.BookShop.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookTitle;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status = OrderStatus.PROCESSING;

    @ManyToOne
    private User user;

    private String numOfBooks;

    public BookOrder(String bookTitle, /*User user*/ String numOfBooks) {
        this.bookTitle = bookTitle;
        //this.user = user;
        this.numOfBooks = numOfBooks;

    }

    public BookOrder() {
    }
}
