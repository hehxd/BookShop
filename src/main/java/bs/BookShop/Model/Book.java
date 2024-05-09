package bs.BookShop.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Basic
    @Column(name = "book_title")
    private String title;

    @Basic
    @Column(name = "book_author")
    private String author;

    @Basic
    @Column(name = "book_description")
    private String description;

    @Basic
    @Column(name = "book_price")
    private Integer price;

    @ManyToMany
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "book_cities",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id")
    )
    private List<City>bookCities;

    public Book(String title, String author, String description, List<Category> categories) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.categories = categories;
    }

    public Book(String title, String author, String description, Integer price, List<Category> categories,
                List<City> bookCities) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
        this.categories = categories;
        this.bookCities = bookCities;
    }
}
