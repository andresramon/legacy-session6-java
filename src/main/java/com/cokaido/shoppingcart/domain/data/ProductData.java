package com.cokaido.shoppingcart.domain.data;

import com.cokaido.shoppingcart.domain.base.EntityData;
import com.cokaido.shoppingcart.domain.base.Id;

public class ProductData extends EntityData {
    public String code;
    public Double price;

    public ProductData(Id id, String code, Double price) {
        super(id);
        this.code = code;
        this.price = price;
    }
}
