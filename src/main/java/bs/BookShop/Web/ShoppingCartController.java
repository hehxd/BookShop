package bs.BookShop.Web;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.ShoppingCart;
import bs.BookShop.Model.dto.CartItemDto;
import bs.BookShop.Service.BookService;
import bs.BookShop.Service.ShoppingCartService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","*"})
@RequestMapping("/api/cart")
public class ShoppingCartController {

    private final BookService bookService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(BookService bookService, ShoppingCartService shoppingCartService) {
        this.bookService = bookService;
        this.shoppingCartService = shoppingCartService;
    }


    @GetMapping("/add/{id}")
    public void addToCart(@RequestBody CartItemDto cartItemDto) {
        shoppingCartService.addToCart(cartItemDto);
    }

    @GetMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id) {
        Book book = bookService.findById(id).get();
        shoppingCartService.removeFromCart(book);
    }


    @GetMapping()
    public ShoppingCart viewCart() {
       return shoppingCartService.getShoppingCart();
    }

    @PostMapping("/add/{id}")
    public void addToCartPost(@RequestBody CartItemDto cartItemDto) {
        shoppingCartService.addToCart(cartItemDto);

    }

    @PostMapping("/remove/{id}")
    public void removeFromCartPost(@PathVariable Long id) {
        Book book = bookService.findById(id).get();
        shoppingCartService.removeFromCart(book);
    }

}
