package com.cokaido.shoppingcart.infrastructure.repositories;

import com.cokaido.shoppingcart.appServices.interfaces.ICustomerRepository;
import com.cokaido.shoppingcart.domain.Customer;
import com.cokaido.shoppingcart.infrastructure.database.InMemoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class CustomerRepository implements ICustomerRepository {

    private final InMemoryDatabase database;

    @Autowired
    public CustomerRepository(InMemoryDatabase database) {
        this.database = database;
    }

    @Override
    public Customer getUserByName(String name) {
        var customer = new Customer();

        customer.restore(Objects.requireNonNull(database.customers.stream().filter(customerData -> customerData.name.equals(name)).findFirst().orElse(null)));

        return customer;
    }

}
