package bs.BookShop.Service.Impl;

import bs.BookShop.Model.Book;
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
    public void addToCart(Book book) {
        ShoppingCart shoppingCart= getOrCreateShoppingCart();
        shoppingCart.getBooks().add(book);
        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() + book.getPrice());
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void removeFromCart(Book book) {
        ShoppingCart shoppingCart= getOrCreateShoppingCart();
        shoppingCart.getBooks().remove(book);
        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() - book.getPrice());
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getShoppingCart() {
        return getOrCreateShoppingCart();
    }

    private ShoppingCart getOrCreateShoppingCart() {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(1L).orElse(null);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCartRepository.save(shoppingCart);
        }
        return shoppingCart;
    }
}
