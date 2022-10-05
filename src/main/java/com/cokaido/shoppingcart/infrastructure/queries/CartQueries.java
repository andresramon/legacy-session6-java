package com.cokaido.shoppingcart.infrastructure.queries;

import com.cokaido.shoppingcart.appServices.data.CartProduct;
import com.cokaido.shoppingcart.appServices.interfaces.ICartQueries;
import com.cokaido.shoppingcart.domain.base.Id;
import com.cokaido.shoppingcart.domain.data.ProductData;
import com.cokaido.shoppingcart.domain.data.ShoppingCartData;
import com.cokaido.shoppingcart.infrastructure.database.InMemoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CartQueries implements ICartQueries {

    private InMemoryDatabase database;

    @Autowired
    public CartQueries(InMemoryDatabase database) {
        this.database = database;
    }

    @Override
    public List<CartProduct> getProductsFromCart(Id shoppingCartId) {

        ShoppingCartData cartData = Objects.requireNonNull(database.shoppingCarts.stream().filter(data -> data.id.equals(shoppingCartId)).findFirst().orElse(null));

        ArrayList<CartProduct> cartProducts = new ArrayList<>();

        for (ProductData productData:cartData.products) {
            cartProducts.add(new CartProduct(productData.code, productData.price));
        }

        return cartProducts;

    }
}
