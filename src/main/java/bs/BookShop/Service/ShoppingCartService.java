package bs.BookShop.Service;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public interface ShoppingCartService {
    void addToCart(Book book, Integer bookQuantity);
    void removeFromCart(Book book);
    ShoppingCart getShoppingCart();
    void clearCart();

}
