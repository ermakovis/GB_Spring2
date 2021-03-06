package ru.geekbrains.happy.market.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.happy.market.beans.Cart;
import ru.geekbrains.happy.market.model.Order;
import ru.geekbrains.happy.market.model.User;
import ru.geekbrains.happy.market.repositories.OrderRepository;
import ru.geekbrains.happy.market.repositories.UserRepository;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
@Data
@Slf4j
public class OrderController {
    private final Cart cart;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal,
                            @RequestParam MultiValueMap<String, String> params) {
        log.info("Order placed");
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        Order order = new Order(cart, user);
        if (params.containsKey("address") && !params.getFirst("address").isBlank()) {
            order.setAddress(params.getFirst("address"));
        }
        orderRepository.save(order);
    }
}