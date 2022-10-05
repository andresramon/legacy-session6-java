package com.cokaido.shoppingcart.appServices.interfaces;

import com.cokaido.shoppingcart.appServices.data.CartProduct;
import com.cokaido.shoppingcart.domain.base.Id;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ICartQueries {

    List<CartProduct> getProductsFromCart(Id shoppingCartId);
}
