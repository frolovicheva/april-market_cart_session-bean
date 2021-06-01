package ru.geekbrains.april.market.dtos;

import lombok.Data;
import ru.geekbrains.april.market.utils.SessionCart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CartDto {
    private List<OrderItemDto> items;
    private BigDecimal sum;

    public CartDto(SessionCart cart) {
        this.items = cart.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.sum = cart.getSum();
    }
}
