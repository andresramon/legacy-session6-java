package com.cokaido.shoppingcart.domain.data;

import com.cokaido.shoppingcart.domain.base.EntityData;
import com.cokaido.shoppingcart.domain.base.Id;

public class CustomerData extends EntityData {
    public String name;

    public CustomerData(Id id, String Name) {
        super(id);
        name = Name;
    }
}
