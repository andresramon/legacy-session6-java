package com.cokaido.shoppingcart.domain;

import com.cokaido.shoppingcart.domain.base.Aggregate;
import com.cokaido.shoppingcart.domain.base.Id;
import com.cokaido.shoppingcart.domain.data.ProductData;

import java.util.ArrayList;

public class Product extends Aggregate<ProductData> {

    private String code;
    private Double price;

    public Product(Id id, String code, Double price) {
        super(id);
        this.code = code;
        this.price = price;
    }

    public Product() {
        super(new Id());
    }

    @Override
    public ProductData toData() {
        return null;
    }

    @Override
    public void restore(ProductData data) {
        id = data.id;
        code = data.code;
        price = data.price;
    }
    public static Iterable<ProductData> getListOfProductsData(Iterable<Product> products)
    {
        ArrayList<ProductData> result  = new ArrayList<>();

        for (Product product:products) {
            result.add(new ProductData(product.id,product.code,product.price));
        }
        return result;
    }

    public static Iterable<Product> getListOfProducts(Iterable<ProductData> products)
    {
        ArrayList<Product> result  = new ArrayList<>();

        for (ProductData product:products) {
            result.add(new Product(product.id,product.code,product.price));
        }
        return result;
    }

}
