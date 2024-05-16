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

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status = OrderStatus.PROCESSING;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "bookOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @ManyToOne
    private ShoppingCart shoppingCart;

    @Column(name = "total_price")
    private double totalPrice;

//    public BookOrder(String bookTitle, /*User user*/ String numOfBooks) {
//        this.bookTitle = bookTitle;
//        //this.user = user;
//        this.numOfBooks = numOfBooks;
//
//    }
    public BookOrder(List<OrderItem> orderItems, ShoppingCart shoppingCart, double totalPrice) {
        this.orderItems = orderItems;
        this.shoppingCart = shoppingCart;
        this.totalPrice = totalPrice;
    }

    public BookOrder() {
    }
}
