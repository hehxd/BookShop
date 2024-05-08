package bs.BookShop.Service.Impl;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.Category;
import bs.BookShop.Repository.BookRepository;
import bs.BookShop.Repository.CategoryRepository;
import bs.BookShop.Service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Book> listBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findByBookId(id);
    }

    @Override
    public Book create(String title, String author, String description, List<Long> categories) {
        List<Category> categoryList = this.categoryRepository.findAllById(categories);
        Book book = new Book(title,author,description,categoryList);
        return this.bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String title, String author, String description, List<Long> categories) {
        Book book = this.bookRepository.findByBookId(id);
        List<Category> categoryList = this.categoryRepository.findAllById(categories);
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setCategories(categoryList);
        return this.bookRepository.save(book);
    }

    @Override
    public Book delete(Long id) {
        Book book = this.bookRepository.findByBookId(id);
        this.bookRepository.deleteById(id);
        return book;
    }
}
