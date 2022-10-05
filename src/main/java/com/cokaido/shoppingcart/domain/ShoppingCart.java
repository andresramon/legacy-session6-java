package com.cokaido.shoppingcart.domain;

import com.cokaido.shoppingcart.domain.base.Aggregate;
import com.cokaido.shoppingcart.domain.base.Id;
import com.cokaido.shoppingcart.domain.data.ShoppingCartData;

import java.util.ArrayList;

public class ShoppingCart extends Aggregate<ShoppingCartData> {

    private final ArrayList<Product> products;
    private Customer customer;

    public ShoppingCart(Id id, Customer customer) {
        super(id);

        this.customer = customer;
        this.products = new ArrayList<>();
    }

    public ShoppingCart() {
        super(new Id());
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product)
    {

         if (product == null )
         {
             throw new IllegalArgumentException();
         }
         products.add(product);
    }

    @Override
    public ShoppingCartData toData() {

        return new ShoppingCartData(id,
                customer.toData(),
                Product.getListOfProductsData(products));
    }

    @Override
    public void restore(ShoppingCartData data) {
        id = data.id;
        customer.restore(data.customer);
        Product.getListOfProducts(data.products).forEach(products::add);
    }
}
