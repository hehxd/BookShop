package bs.BookShop.Service.Impl;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.Category;
import bs.BookShop.Model.City;
import bs.BookShop.Model.exceptions.InvalidCityIdException;
import bs.BookShop.Repository.BookRepository;
import bs.BookShop.Repository.CategoryRepository;
import bs.BookShop.Repository.CityRepository;
import bs.BookShop.Service.BookService;
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
    public Book findById(Long id) {
        return this.bookRepository.findByBookId(id);
    }

    @Override
    public Book create(String title, String author, String description, Integer price,
                       List<Long> categories, List<Long> cities) {
        List<Category> categoryList = this.categoryRepository.findAllById(categories);
        List<City> cityList = this.cityRepository.findAllById(cities);
        Book book = new Book(title, author, description, price, categoryList, cityList);
        return this.bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String title, String author, String description,
                       Integer price, List<Long> categories, List<Long> cities) {
        Book book = this.bookRepository.findByBookId(id);
        List<Category> categoryList = this.categoryRepository.findAllById(categories);
        List<City> cityList = this.cityRepository.findAllById(cities);
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setPrice(price);
        book.setCategories(categoryList);
        book.setBookCities(cityList);
        return this.bookRepository.save(book);
    }

    @Override
    public Book delete(Long id) {
        Book book = this.bookRepository.findByBookId(id);
        this.bookRepository.deleteById(id);
        return book;
    }

    @Override
    public List<Book> searchBooks(Long categoryId, Integer price, Long cityId) {
        if (categoryId != null && price != null && cityId != null) {
            Category category = this.categoryRepository.findByCategoryId(categoryId);
            City city = this.cityRepository.findById(cityId).orElseThrow(InvalidCityIdException::new);
            return bookRepository.findByCategoriesAndPriceEqualsAndBookCitiesContains(category, price, city);
        } else if (categoryId != null && price != null) {
            Category category = this.categoryRepository.findByCategoryId(categoryId);
            return bookRepository.findByCategoriesAndPriceEquals(category, price);
        } else if (categoryId != null && cityId != null) {
            Category category = this.categoryRepository.findByCategoryId(categoryId);
            City city = this.cityRepository.findById(cityId).orElseThrow(InvalidCityIdException::new);
            return bookRepository.findByCategoriesAndBookCitiesContains(category, city);
        } else if (price != null && cityId != null) {
            City city = this.cityRepository.findById(cityId).orElseThrow(InvalidCityIdException::new);
            return bookRepository.findByPriceEqualsAndBookCitiesContains(price, city);
        } else if (categoryId != null) {
            Category category = this.categoryRepository.findByCategoryId(categoryId);
            return bookRepository.findByCategories(category);
        } else if (price != null) {
            return bookRepository.findBookByPriceEquals(price);
        } else if (cityId != null) {
            City city = this.cityRepository.findById(cityId).orElseThrow(InvalidCityIdException::new);
            return bookRepository.findBookByBookCitiesContains(city);
        }
        return bookRepository.findAll();
    }
}
