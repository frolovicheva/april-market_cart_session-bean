package ru.geekbrains.april.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.april.market.models.OrderItem;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.utils.SessionCart;


@Service
@RequiredArgsConstructor
public class SessionCartService {
    private final SessionCart sessionCart;
    private final ProductService productService;


    public void addToCart(Long id) {
        for (OrderItem orderItem : sessionCart.getItems ()) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.incrementQuantity();
                sessionCart.recalculate ();
                return;
            }
        }
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + id + " (add to cart)"));
        OrderItem item = new OrderItem(product);
        sessionCart.addItem (item);
        sessionCart.recalculate();
    }

}
