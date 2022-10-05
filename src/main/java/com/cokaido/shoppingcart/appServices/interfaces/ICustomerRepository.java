package com.cokaido.shoppingcart.appServices.interfaces;

import com.cokaido.shoppingcart.domain.Customer;
import org.springframework.stereotype.Repository;


public interface ICustomerRepository {
     Customer getUserByName(String name);
}
