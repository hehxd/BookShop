package bs.BookShop.Web;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.ShoppingCart;
import bs.BookShop.Service.BookService;
import bs.BookShop.Service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShoppingCartController {

    private final BookService bookService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(BookService bookService, ShoppingCartService shoppingCartService) {
        this.bookService = bookService;
        this.shoppingCartService = shoppingCartService;
    }


    @GetMapping("/books/cart/add/{id}")
    public String addToCart(@PathVariable Long id) {
        Book book = bookService.findById(id);
        shoppingCartService.addToCart(book);
        return "redirect:/";
    }

    @GetMapping("/books/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        Book book = bookService.findById(id);
        shoppingCartService.removeFromCart(book);
        return "redirect:/";
    }


    @GetMapping("/books/cart")
    public String viewCart(Model model) {
        ShoppingCart cart = shoppingCartService.getShoppingCart();
        model.addAttribute("cart", cart);
        return "cart-view";
    }

    @PostMapping("/books/cart/add/{id}")
    public String addToCartPost(@PathVariable Long id) {
        Book book = bookService.findById(id);
        shoppingCartService.addToCart(book);
        return "redirect:/";
    }

    @PostMapping("/books/cart/remove/{id}")
    public String removeFromCartPost(@PathVariable Long id) {
        Book book = bookService.findById(id);
        shoppingCartService.removeFromCart(book);
        return "redirect:/";
    }

}
