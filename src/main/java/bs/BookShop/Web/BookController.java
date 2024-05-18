package bs.BookShop.Web;

import bs.BookShop.Model.*;
import bs.BookShop.Model.dto.BookDto;
import bs.BookShop.Model.dto.ReviewDto;
import bs.BookShop.Model.dto.UserDto;
import bs.BookShop.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","*"})
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final CityService cityService;
    private final ReviewService reviewService;

    private final UserService userService;

    public BookController(BookService bookService, CategoryService categoryService, CityService cityService, ReviewService reviewService, UserService userService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.cityService = cityService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping()
    public List<Book> showProduct(@RequestParam(required = false) Long categoryId,
                                  @RequestParam(required = false) Integer price,
                                  @RequestParam(required = false) Long cityId,
                                  @RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author) {
        List<Book> books=null;
        if (categoryId == null && price==null && cityId==null && title==null && author==null) {
            books = this.bookService.listBooks();
        } else {
            books = this.bookService.searchBooks(categoryId, price, cityId, title, author) ;
        }

        return books;
    }

//    @GetMapping("/add")
//    public String showAdd(Model model){
//        model.addAttribute("categories", categoryService.listCategories());
//        model.addAttribute("cities", cityService.listAll());
//        return "add-edit";
//    }

    @PostMapping("/add")
    public ResponseEntity<Book> create(@RequestBody BookDto bookDto) {
        return this.bookService.create(bookDto).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

//    @GetMapping("/edit/{id}")
//    public String showEdit(@PathVariable Long id,Model model) {
//        Book book = this.bookService.findById(id);
//        model.addAttribute("categories", categoryService.listCategories());
//        model.addAttribute("cities", cityService.listAll());
//        model.addAttribute("book", book);
//        return "add-edit";
//    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id,
                                       @RequestBody BookDto bookDto) {
        return this.bookService.update(id,bookDto).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        this.bookService.delete(id);
        if(this.bookService.findById(id).isPresent())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> bookPage(@PathVariable Long id){
        return this.bookService.findById(id).map((book -> ResponseEntity.ok().body(book)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    int i = 10;
    @PostMapping("/{id}/addReview")
    public ResponseEntity<Review> addReview(@RequestBody ReviewDto reviewDto) {

        //For test purposes
        UserDto test = new UserDto("testFirsName"+(i+1),"testLastName"+(i+1),"testAddress"+(i+1));
        User user = userService.create(test).get();
        reviewDto.setUser(user.getId());


        return this.reviewService.create(reviewDto).map(review -> ResponseEntity.ok().body(review))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/checkout")
    public String viewCheckout() {

        return "checkout";
    }

    @GetMapping("/categories")
    public List<Category> getCategories()
    {
        return this.categoryService.listCategories();
    }

    @GetMapping("/cities")
    public List<City> getCities()
    {
        return this.cityService.listAll();
    }
}
