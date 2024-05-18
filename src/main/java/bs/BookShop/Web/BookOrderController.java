package bs.BookShop.Web;

import bs.BookShop.Model.*;
import bs.BookShop.Service.OrderService;
import bs.BookShop.Service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","*"})
@RequestMapping("/api/bookorder")
public class BookOrderController {

    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    public BookOrderController(OrderService orderService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/confirmation")
    public ResponseEntity<BookOrder> confirmOrder() {
        ShoppingCart cart = shoppingCartService.getShoppingCart();
        return this.orderService.placeOrder(cart).map(order -> ResponseEntity.ok().body(order))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @GetMapping()
    public List<BookOrder> viewOrders() {
       return orderService.listAll();
    }

    @PostMapping("/{id}/updateStatus")
    public void updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(id, status);
    }
}
