package bs.BookShop.Service.Impl;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.CartItem;
import bs.BookShop.Model.ShoppingCart;
import bs.BookShop.Repository.ShoppingCartRepository;
import bs.BookShop.Service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public void addToCart(Book book, Integer quantity) {
        ShoppingCart shoppingCart = getOrCreateShoppingCart();
        CartItem existingCartItem = findCartItemInCart(shoppingCart, book);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setBook(book);
            newCartItem.setQuantity(quantity);
            newCartItem.setShoppingCart(shoppingCart);
            shoppingCart.getCartItems().add(newCartItem);
        }

        shoppingCart.setTotalPrice(calculateTotalPrice(shoppingCart));
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void removeFromCart(Book book) {
        ShoppingCart shoppingCart = getOrCreateShoppingCart();
        CartItem cartItem = findCartItemInCart(shoppingCart, book);

        if (cartItem != null) {
            shoppingCart.getCartItems().remove(cartItem);
            shoppingCart.setTotalPrice(calculateTotalPrice(shoppingCart));
            shoppingCartRepository.save(shoppingCart);
        }
    }

    @Override
    public ShoppingCart getShoppingCart() {
        return getOrCreateShoppingCart();
    }

    @Override
    public void clearCart() {
        ShoppingCart shoppingCart = getShoppingCart();
        if (shoppingCart != null) {
            shoppingCart.getCartItems().clear();
            shoppingCart.setTotalPrice(0.0);
            shoppingCartRepository.save(shoppingCart);
        }
    }

    private ShoppingCart getOrCreateShoppingCart() {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(1L).orElse(null);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCartRepository.save(shoppingCart);
        }
        return shoppingCart;
    }

    private CartItem findCartItemInCart(ShoppingCart shoppingCart, Book book) {
        return shoppingCart.getCartItems()
                .stream()
                .filter(cartItem -> cartItem.getBook().equals(book))
                .findFirst()
                .orElse(null);
    }

    private double calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems().stream()
                .mapToDouble(cartItem -> cartItem.getBook().getPrice() * cartItem.getQuantity())
                .sum();
    }
}

