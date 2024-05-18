package bs.BookShop.Service.Impl;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.Category;
import bs.BookShop.Model.City;
import bs.BookShop.Model.dto.BookDto;
import bs.BookShop.Repository.BookRepository;
import bs.BookShop.Repository.CategoryRepository;
import bs.BookShop.Repository.CityRepository;
import bs.BookShop.Service.BookService;
import bs.BookShop.Specification.BookSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final CityRepository cityRepository;

    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository, CityRepository cityRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<Book> listBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findByBookId(id));
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        List<Category> categoryList = this.categoryRepository.findAllById(bookDto.getCategories());
        List<City> cityList = this.cityRepository.findAllById(bookDto.getBookCities());
        Book book = new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getDescription(), bookDto.getPrice(), categoryList, cityList);
        return Optional.of(this.bookRepository.save(book));
    }

    public Optional<Book> update(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findByBookId(id);
        List<Category> categoryList = this.categoryRepository.findAllById(bookDto.getCategories());
        List<City> cityList = this.cityRepository.findAllById(bookDto.getBookCities());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setDescription(bookDto.getDescription());
        book.setPrice(bookDto.getPrice());
        book.setCategories(categoryList);
        book.setBookCities(cityList);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Book delete(Long id) {
        Book book = this.bookRepository.findByBookId(id);
        this.bookRepository.deleteById(id);
        return book;
    }

    @Override
    public List<Book> searchBooks(Long categoryId, Integer price, Long cityId, String title, String author) {
        Specification<Book> spec = Specification.where(null);

        if (categoryId != null) {
            spec = spec.and(BookSpecification.hasCategory(categoryId));
        }

        if (price != null) {
            spec = spec.and(BookSpecification.hasPrice(price));
        }

        if (cityId != null) {
            spec = spec.and(BookSpecification.hasCity(cityId));
        }

        if (title != null) {
            spec = spec.and(BookSpecification.hasTitle(title));
        }

        if (author != null) {
            spec = spec.and(BookSpecification.hasAuthor(author));
        }

//        if (price != null) {  TODO: If we want to go up to price VS matching the exact price (method above)
//            spec = spec.and(BookSpecification.priceUpTo(price));
//        }

        return bookRepository.findAll(spec);
    }


}
