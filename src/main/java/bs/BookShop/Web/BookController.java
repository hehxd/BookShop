package bs.BookShop.Web;

import bs.BookShop.Model.*;
import bs.BookShop.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.query.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final CityService cityService;
    private final OrderService orderService;

    private final ShoppingCartService shoppingCartService;

    public BookController(BookService bookService, CategoryService categoryService, CityService cityService, OrderService orderService, ShoppingCartService shoppingCartService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.cityService = cityService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping({"/","/books"})
    public String showProduct(@RequestParam(required = false) Long categoryId,
                              @RequestParam(required = false) Integer price,
                              @RequestParam(required = false) Long cityId, Model model) {
        List<Book> books=null;
        if (categoryId == null && price==null && cityId==null) {
            books = this.bookService.listBooks();
        } else {
            books = this.bookService.searchBooks(categoryId, price, cityId);
        }

        model.addAttribute("books", books);
        model.addAttribute("categories", this.categoryService.listCategories());
        model.addAttribute("cities", this.cityService.listAll());
        return "index";
    }

    @GetMapping("/books/add")
    public String showAdd(Model model){
        model.addAttribute("categories", categoryService.listCategories());
        model.addAttribute("cities", cityService.listAll());
        return "add-edit";
    }

    @PostMapping("/books/add")
    public String create(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer price,
            @RequestParam List<Long> cities,
            @RequestParam List<Long> categories) {
        this.bookService.create(title,author,description, price, categories, cities);
        return "redirect:/";
    }

    @GetMapping("/books/edit/{id}")
    public String showEdit(@PathVariable Long id,Model model) {
        Book book = this.bookService.findById(id);
        model.addAttribute("categories", categoryService.listCategories());
        model.addAttribute("cities", cityService.listAll());
        model.addAttribute("book", book);
        return "add-edit";
    }

    @PostMapping("/books/edit/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam(required = false) String title,
                         @RequestParam(required = false) String author,
                         @RequestParam(required = false) String description,
                         @RequestParam(required = false) Integer price,
                         @RequestParam List<Long> cities,
                         @RequestParam List<Long> categories) {
        this.bookService.update(id, title, author, description, price, categories, cities);
        return "redirect:/";
    }

    @PostMapping("/books/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.bookService.delete(id);
        return "redirect:/";
    }


    @GetMapping("/books/{id}")
    public String bookPage(@PathVariable Long id, Model model){
        Book book = bookService.findById(id);
        model.addAttribute("book", book);

        return "book-page";
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
    @PostMapping("/books/order/{id}")
    public String submitOrder(@PathVariable Long id, Model model){
        Book book = bookService.findById(id);
        model.addAttribute("book", book);

        return "orderConfirmation";
    }
    @PostMapping("/books/order/confirmation/{id}")
    public String confirmOrder(@PathVariable Long id, Model model){
        Book book = bookService.findById(id);
        BookOrder bookOrder = new BookOrder(book.getTitle());
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
