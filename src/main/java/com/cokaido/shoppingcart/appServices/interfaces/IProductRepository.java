package com.cokaido.shoppingcart.appServices.interfaces;

import com.cokaido.shoppingcart.domain.Product;
import org.springframework.stereotype.Repository;


public interface IProductRepository {

    public Product getByCode(String productCode);
}
