package bs.BookShop.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private ShoppingCart shoppingCart;

    private int quantity;

    public CartItem(Book book, ShoppingCart shoppingCart, int quantity) {
        this.book = book;
        this.shoppingCart = shoppingCart;
        this.quantity = quantity;
    }
}
