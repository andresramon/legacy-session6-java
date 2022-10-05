package com.cokaido.shoppingcart.domain.services;

import com.cokaido.shoppingcart.appServices.data.CartProduct;
import org.springframework.stereotype.Component;


public interface ICartPriceService {
    Double calculateCart(Iterable<CartProduct> products);
}

