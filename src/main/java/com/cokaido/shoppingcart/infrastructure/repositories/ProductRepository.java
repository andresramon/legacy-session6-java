package com.cokaido.shoppingcart.infrastructure.repositories;

import com.cokaido.shoppingcart.appServices.interfaces.IProductRepository;
import com.cokaido.shoppingcart.domain.Customer;
import com.cokaido.shoppingcart.domain.Product;
import com.cokaido.shoppingcart.infrastructure.database.InMemoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class ProductRepository implements IProductRepository {

    private InMemoryDatabase database;

    @Autowired
    public ProductRepository(InMemoryDatabase database) {
        this.database = database;
    }

    @Override
    public Product getByCode(String productCode) {
        Product product = new Product();

        product.restore(Objects.requireNonNull(database.products.stream().filter(data -> data.code.equals(productCode)).findFirst().orElse(null)));

        return product;
    }
}
