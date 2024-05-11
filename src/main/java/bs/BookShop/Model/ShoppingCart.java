package bs.BookShop.Model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shoppingCartId")
    private Long shoppingCartId;

    @OneToMany
    private List<Book> books;
    @Basic
    @Column(name = "total_price")
    private double totalPrice;

    public ShoppingCart(List<Book> books, double totalPrice) {
        this.books = new ArrayList<>();
        this.totalPrice = 0.0;
    }
}
