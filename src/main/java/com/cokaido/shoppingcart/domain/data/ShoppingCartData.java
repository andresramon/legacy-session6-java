package com.cokaido.shoppingcart.domain.data;

import com.cokaido.shoppingcart.domain.base.EntityData;
import com.cokaido.shoppingcart.domain.base.Id;

public class ShoppingCartData extends EntityData {
    public CustomerData customer;
    public Iterable<ProductData> products;

    public ShoppingCartData(Id id, CustomerData customer, Iterable<ProductData> products) {
        super(id);
        this.customer = customer;
        this.products = products;
    }
}
