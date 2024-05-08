package bs.BookShop.Service;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.Category;

import java.util.List;

public interface BookService {
    List<Book> listBooks();
    Book findById(Long id);
    Book create(String title, String author, String description, List<Long> categories);
    Book update(Long id, String title, String author, String description, List<Long> categories);
    Book delete(Long id);
}
