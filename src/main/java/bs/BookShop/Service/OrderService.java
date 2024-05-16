package bs.BookShop.Service;

import bs.BookShop.Model.BookOrder;
import bs.BookShop.Model.OrderStatus;
import bs.BookShop.Model.ShoppingCart;

import java.util.List;

public interface OrderService {
    BookOrder placeOrder(ShoppingCart shoppingCart);
    BookOrder findById(Long id);
    List<BookOrder> listAll();
    void updateOrderStatus(Long orderId, OrderStatus status);
}
