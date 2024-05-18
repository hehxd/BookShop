package bs.BookShop.Model.dto;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.ShoppingCart;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CartItemDto {

    private Long book;


    private Long shoppingCart;

    private int quantity;

    public CartItemDto(Long book, Long shoppingCart, int quantity) {
        this.book = book;
        this.shoppingCart = shoppingCart;
        this.quantity = quantity;
    }
}
