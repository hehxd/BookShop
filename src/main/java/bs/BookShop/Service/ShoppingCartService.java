package bs.BookShop.Service;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.ShoppingCart;
import bs.BookShop.Model.dto.CartItemDto;
import org.springframework.stereotype.Service;

@Service
public interface ShoppingCartService {
    void addToCart(CartItemDto cartItemDto);
    void removeFromCart(Book book);
    ShoppingCart getShoppingCart();
    void clearCart();

}
