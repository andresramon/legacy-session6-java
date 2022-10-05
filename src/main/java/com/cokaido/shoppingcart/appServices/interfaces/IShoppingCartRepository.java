package com.cokaido.shoppingcart.appServices.interfaces;

import com.cokaido.shoppingcart.domain.ShoppingCart;
import com.cokaido.shoppingcart.domain.base.Id;
import org.springframework.stereotype.Repository;


public interface IShoppingCartRepository {

     void create(ShoppingCart cart);
     ShoppingCart get(Id id);
     void save(ShoppingCart cart);
}
