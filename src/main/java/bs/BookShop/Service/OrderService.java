package bs.BookShop.Service;

import bs.BookShop.Model.BookOrder;
import bs.BookShop.Model.OrderStatus;
import bs.BookShop.Model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<BookOrder> placeOrder(ShoppingCart shoppingCart);
    Optional<BookOrder> findById(Long id);
    List<BookOrder> listAll();
    void updateOrderStatus(Long orderId, OrderStatus status);
}
