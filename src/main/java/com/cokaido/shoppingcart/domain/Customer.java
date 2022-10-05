package com.cokaido.shoppingcart.domain;

import com.cokaido.shoppingcart.domain.base.Aggregate;
import com.cokaido.shoppingcart.domain.base.Id;
import com.cokaido.shoppingcart.domain.data.CustomerData;

public class Customer extends Aggregate<CustomerData> {

    private String name;

    public Customer(Id id, String name) {

        super(id);
        this.name = name;
    }

    public Customer() {
        super(new Id());
    }

    @Override
    public CustomerData toData() {
        return new CustomerData(id,name);
    }

    @Override
    public void restore(CustomerData data) {
        id = data.id;
        name = data.name;
    }
}
