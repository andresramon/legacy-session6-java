package com.cokaido.shoppingcart.infrastructure.database;

import com.cokaido.shoppingcart.domain.base.Id;
import com.cokaido.shoppingcart.domain.data.CustomerData;
import com.cokaido.shoppingcart.domain.data.ProductData;
import com.cokaido.shoppingcart.domain.data.ShoppingCartData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class InMemoryDatabase {

    public ArrayList<ProductData> products = new ArrayList<>();
    public ArrayList<CustomerData> customers = new ArrayList<>();
    public ArrayList<ShoppingCartData> shoppingCarts = new ArrayList<>();

    public InMemoryDatabase() {
        InitializeDatabase();
    }

    private void InitializeDatabase() {
        customers.add( new CustomerData(new Id(),"John"));
        products.add(new ProductData(new Id(),"VOUCHER",5.0));
        products.add(new ProductData(new Id(),"T-SHIRT",20.0));
        products.add(new ProductData(new Id(),"MUG",7.5));
    }
}
