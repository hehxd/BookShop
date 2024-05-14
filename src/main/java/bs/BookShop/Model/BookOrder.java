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
    //@JoinTable(
      //      name = "user_book_orders",
        //    joinColumns = @JoinColumn(name = "book_order_id"),
          //  inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    public BookOrder(String bookTitle /*User user*/) {
        this.bookTitle = bookTitle;
        //this.user = user;
    }

    public BookOrder() {
    }
}
