package ru.geekbrains.happy.market.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.happy.market.beans.Cart;
import ru.geekbrains.happy.market.model.Order;
import ru.geekbrains.happy.market.model.OrderItem;
import ru.geekbrains.happy.market.model.User;
import ru.geekbrains.happy.market.repositories.OrderItemRepository;
import ru.geekbrains.happy.market.repositories.OrderRepository;
import ru.geekbrains.happy.market.repositories.UserRepository;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@Data
public class OrderController {
    private Cart cart;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private UserRepository userRepository;

    //Как-то в других контроллерах бех Autowire работает, но у меня без этого не стартует
    @Autowired
    public OrderController(Cart cart,
                           OrderRepository orderRepository,
                           OrderItemRepository orderItemRepository,
                           UserRepository userRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal) {
        List<OrderItem> items = cart.getItems();
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        Order order = new Order(user);
        order = orderRepository.save(order);
        for (OrderItem item : items) {
            item.setOrder(order);
        }
        orderItemRepository.saveAll(items);
    }
}
