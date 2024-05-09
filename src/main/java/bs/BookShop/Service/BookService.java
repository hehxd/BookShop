package bs.BookShop.Service;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.Category;

import java.util.List;

public interface BookService {
    List<Book> listBooks();
    Book findById(Long id);
    Book create(String title, String author, String description, Integer price, List<Long> categories, List<Long>cities);
    Book update(Long id, String title, String author, String description, Integer price,
                List<Long> categories, List<Long>cities);
    Book delete(Long id);
    List<Book> searchBooks(Long categoryId, Integer price, Long cityId);
}
