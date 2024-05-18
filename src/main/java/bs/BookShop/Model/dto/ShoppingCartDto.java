package bs.BookShop.Model.dto;

import bs.BookShop.Model.CartItem;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCartDto {
    private List<Long> cartItems;


    private double totalPrice;

    public ShoppingCartDto(List<Long> cartItems, double totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }
}
