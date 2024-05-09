package bs.BookShop.Repository;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.Category;
import bs.BookShop.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookId(Long id);
    List<Book>findBookByPriceEquals(Integer price);
    List<Book>findByCategories(Category category);
    List<Book>findBookByBookCitiesContains(City city);
    List<Book> findByCategoriesAndPriceEqualsAndBookCitiesContains(Category category, Integer price, City city);
 //   List<Book>findByCategoriesAndPriceEqualsAndBookCities()
    List<Book>findByCategoriesAndPriceEquals(Category category, Integer price);
    List<Book>findByCategoriesAndBookCitiesContains(Category category, City city);
    List<Book>findByPriceEqualsAndBookCitiesContains(Integer price, City city);
}
