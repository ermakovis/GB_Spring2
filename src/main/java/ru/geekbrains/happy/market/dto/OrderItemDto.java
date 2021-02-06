package ru.geekbrains.happy.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.happy.market.model.OrderItem;
import ru.geekbrains.happy.market.model.Product;

@NoArgsConstructor
@Data
public class OrderItemDto {
    private String productTitle;
    private int quantity;
    private int price;

    public OrderItemDto(OrderItem orderItem) {
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
    }
}
