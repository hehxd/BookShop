package bs.BookShop.Web;

import bs.BookShop.Model.*;
import bs.BookShop.Service.BookService;
import bs.BookShop.Service.OrderService;
import bs.BookShop.Service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookOrderController {

    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    public BookOrderController(OrderService orderService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/books/order/confirmation")
    public String confirmOrder(Model model) {
        ShoppingCart cart = shoppingCartService.getShoppingCart();
        BookOrder order = orderService.placeOrder(cart);
        model.addAttribute("order", order);

        shoppingCartService.clearCart();
        return "redirect:/books/orders";
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
