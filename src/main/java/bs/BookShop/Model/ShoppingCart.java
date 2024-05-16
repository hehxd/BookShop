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
    @Column(name = "shopping_cart_id")
    private Long id;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @Basic
    @Column(name = "total_price")
    private double totalPrice;

    public ShoppingCart(List<CartItem> cartItems, double totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }
}
