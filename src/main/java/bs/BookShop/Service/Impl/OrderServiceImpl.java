package bs.BookShop.Service.Impl;

import bs.BookShop.Model.*;
import bs.BookShop.Repository.OrderRepository;
import bs.BookShop.Service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public BookOrder placeOrder(ShoppingCart shoppingCart) {
        BookOrder order = new BookOrder();
        order.setShoppingCart(shoppingCart);
        order.setTotalPrice(shoppingCart.getTotalPrice());

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setBookOrder(order);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        return orderRepository.save(order);
    }

    @Override
    public BookOrder findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public List<BookOrder> listAll() {
        return orderRepository.findAll();
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) {
        BookOrder order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            orderRepository.save(order);
        }
    }

}
