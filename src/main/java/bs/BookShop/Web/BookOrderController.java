package bs.BookShop.Web;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.BookOrder;
import bs.BookShop.Model.OrderStatus;
import bs.BookShop.Service.BookService;
import bs.BookShop.Service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookOrderController {

    private final BookService bookService;
    private final OrderService orderService;

    public BookOrderController(BookService bookService, OrderService orderService) {
        this.bookService = bookService;
        this.orderService = orderService;
    }


    @PostMapping("/books/order/{id}")
    public String submitOrder(@PathVariable Long id, Model model){
        Book book = bookService.findById(id);
        model.addAttribute("book", book);

        return "orderConfirmation";
    }
    @PostMapping("/books/order/confirmation/{id}")
    public String confirmOrder(@PathVariable Long id, @RequestParam("numOfBooks") String numOfBooks,
                               Model model){
        Book book = bookService.findById(id);
        BookOrder bookOrder = new BookOrder(book.getTitle(), numOfBooks);
        orderService.submitOrder(bookOrder);
        return "redirect:/";
    }

    @GetMapping("/books/orders")
    public String viewOrders(Model model) {
        List<BookOrder> bookOrders = orderService.listAll();
        model.addAttribute("orders", bookOrders);
        return "orderView";
    }

    @PostMapping("books/orders/{id}/updateStatus")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(id, status);
        return "redirect:/books/orders";
    }
}
