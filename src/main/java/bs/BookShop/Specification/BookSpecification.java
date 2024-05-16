package bs.BookShop.Specification;


import bs.BookShop.Model.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> hasCategory(Long categoryId) {
        return (book, cq, cb) -> cb.equal(book.get("categories").get("id"), categoryId);
    }

    public static Specification<Book> hasPrice(Integer price) {
        return (book, cq, cb) -> cb.equal(book.get("price"), price);
    }

    public static Specification<Book> hasCity(Long cityId) {
        return (book, cq, cb) -> cb.equal(book.get("bookCities").get("id"), cityId);
    }

    public static Specification<Book> hasTitle(String title) {
        return (book, cq, cb) -> cb.like(cb.lower(book.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Book> hasAuthor(String author) {
        return (book, cq, cb) -> cb.like(cb.lower(book.get("author")), "%" + author.toLowerCase() + "%");

    }

    public static Specification<Book> priceUpTo(Integer price) {
        return (book, cq, cb) -> cb.lessThanOrEqualTo(book.get("price"), price);
    }

}
