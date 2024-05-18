package bs.BookShop.Service;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();
    Optional<Book> findById(Long id);
    Optional<Book> create(BookDto bookDto);
    Optional<Book> update(Long id, BookDto bookDto);
    Book delete(Long id);
    List<Book> searchBooks(Long categoryId, Integer price, Long cityId, String title, String author);
}
