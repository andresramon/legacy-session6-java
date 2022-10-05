package com.cokaido.shoppingcart.infrastructure.repositories;

import com.cokaido.shoppingcart.appServices.interfaces.IShoppingCartRepository;
import com.cokaido.shoppingcart.domain.Product;
import com.cokaido.shoppingcart.domain.ShoppingCart;
import com.cokaido.shoppingcart.domain.base.Id;
import com.cokaido.shoppingcart.domain.data.ShoppingCartData;
import com.cokaido.shoppingcart.infrastructure.database.InMemoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;
@Repository
public class ShoppingCartRepository implements IShoppingCartRepository
{
    private final InMemoryDatabase database;

    @Autowired
    public ShoppingCartRepository(InMemoryDatabase database) {
        this.database = database;
    }

    @Override
    public void create(ShoppingCart cart) {
        database.shoppingCarts.add(cart.toData());
    }

    @Override
    public ShoppingCart get(Id id) {
        var cart = new ShoppingCart();

        cart.restore(Objects.requireNonNull(database.shoppingCarts.stream().filter(data -> data.id.equals(id)).findFirst().orElse(null)));

        return cart;
    }

    @Override
    public void save(ShoppingCart cart) {

        int cartPosition=0;
        for (int i = 0; i <  database.shoppingCarts.size(); i++) {
            ShoppingCartData cartData = database.shoppingCarts.get(i);
            if (cartData.id.equals(cart.id)) {
                cartPosition = i;
            }
        }
        database.shoppingCarts.set(cartPosition,cart.toData());
    }
}
