package ru.geekbrains.april.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.april.market.dtos.CartDto;
import ru.geekbrains.april.market.services.SessionCartService;
import ru.geekbrains.april.market.utils.SessionCart;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class SessionCartController {
    private final SessionCart sessionCart;
    private final SessionCartService sessionCartService;

    @GetMapping("/add/{productId}")
    public void addToCart(@PathVariable(name = "productId") Long id) {
        sessionCartService.addToCart (id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        sessionCart.clear();
    }

    @GetMapping
    public CartDto getCart() {
        return new CartDto(sessionCart);
    }
}
