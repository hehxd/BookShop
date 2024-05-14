package bs.BookShop.Service.Impl;

import bs.BookShop.Model.BookOrder;
import bs.BookShop.Model.OrderStatus;
import bs.BookShop.Repository.OrderRepository;
import bs.BookShop.Service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public BookOrder submitOrder(BookOrder order){
        orderRepository.save(order);
        return order;
    }

    @Override
    public BookOrder findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public List<BookOrder> listAll() {
        return orderRepository.findAll();
    }


}
