package com.cokaido.shoppingcart.domain.services;

import com.cokaido.shoppingcart.appServices.data.CartProduct;
import org.springframework.stereotype.Component;

@Component
public class CartPriceService implements ICartPriceService {

    @Override
    public Double calculateCart(Iterable<CartProduct> products) {
        Double result = 0.0;
        for (CartProduct product : products) {
            result += product.price;
        }
        return result;
    }
}
