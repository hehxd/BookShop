package bs.BookShop.Service;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.BookOrder;
import bs.BookShop.Model.OrderStatus;

import java.util.List;

public interface OrderService {
    BookOrder submitOrder(BookOrder order);
    BookOrder findById(Long id);
    List<BookOrder> listAll();
}
