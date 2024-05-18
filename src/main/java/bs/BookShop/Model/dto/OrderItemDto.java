package bs.BookShop.Model.dto;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.BookOrder;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class OrderItemDto {

    private Long book;


    private Long bookOrder;

    private int quantity;

    public OrderItemDto(Long book, Long bookOrder, int quantity) {
        this.book = book;
        this.bookOrder = bookOrder;
        this.quantity = quantity;
    }
}
