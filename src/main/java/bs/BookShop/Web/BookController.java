package bs.BookShop.Web;

import bs.BookShop.Model.Book;
import bs.BookShop.Service.BookService;
import bs.BookShop.Service.CategoryService;
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

    public BookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping({"/","/books"})
    public String showProduct(Model model) {
        model.addAttribute("books", bookService.listBooks());
        return "index";
    }

    @GetMapping("/books/add")
    public String showAdd(Model model){
        model.addAttribute("categories", categoryService.listCategories());
        return "add-edit";
    }

    @PostMapping("/books/add")
    public String create(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String description,
            @RequestParam List<Long> categories) {
        this.bookService.create(title,author,description,categories);
        return "redirect:/";
    }

    @GetMapping("/books/edit/{id}")
    public String showEdit(@PathVariable Long id,Model model) {
        Book book = this.bookService.findById(id);
        model.addAttribute("categories", categoryService.listCategories());
        model.addAttribute("book", book);
        return "add-edit";
    }

    @PostMapping("/books/edit/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String author,
                         @RequestParam String description,
                         @RequestParam List<Long> categories) {
        this.bookService.update(id, title, author, description, categories);
        return "redirect:/";
    }

    @PostMapping("/books/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.bookService.delete(id);
        return "redirect:/";
    }

}
