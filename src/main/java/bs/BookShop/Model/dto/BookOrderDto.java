package bs.BookShop.Model.dto;

import bs.BookShop.Model.OrderItem;
import bs.BookShop.Model.OrderStatus;
import bs.BookShop.Model.ShoppingCart;
import bs.BookShop.Model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class BookOrderDto {

    private OrderStatus status;


    private Long user;


    private List<Long> orderItems;


    private Long shoppingCart;


    private double totalPrice;

    public BookOrderDto(OrderStatus status, Long user, List<Long> orderItems, Long shoppingCart, double totalPrice) {
        this.status = status;
        this.user = user;
        this.orderItems = orderItems;
        this.shoppingCart = shoppingCart;
        this.totalPrice = totalPrice;
    }
}
