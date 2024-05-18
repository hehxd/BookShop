package bs.BookShop.Model.dto;

import bs.BookShop.Model.Category;
import bs.BookShop.Model.City;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {

    private String title;


    private String author;


    private String description;


    private Integer price;


    private List<Long> categories;


    private List<Long>bookCities;

    private String bookCover;

    public BookDto(String title, String author, String description, Integer price, List<Long> categories, List<Long> bookCities,String bookCover) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
        this.categories = categories;
        this.bookCities = bookCities;
        this.bookCover= bookCover;
    }
}
