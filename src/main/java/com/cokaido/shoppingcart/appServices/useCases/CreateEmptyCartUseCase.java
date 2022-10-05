package com.cokaido.shoppingcart.appServices.useCases;

import com.cokaido.shoppingcart.appServices.interfaces.ICustomerRepository;
import com.cokaido.shoppingcart.appServices.interfaces.IShoppingCartRepository;
import com.cokaido.shoppingcart.appServices.useCases.exceptions.CustomerNotFound;
import com.cokaido.shoppingcart.domain.Customer;
import com.cokaido.shoppingcart.domain.ShoppingCart;
import com.cokaido.shoppingcart.domain.base.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CreateEmptyCartUseCase {

    private final IShoppingCartRepository shoppingCartRepository;
    private final ICustomerRepository customerRepository;

    @Autowired
    public CreateEmptyCartUseCase(IShoppingCartRepository shoppingCartRepository, ICustomerRepository customerRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerRepository = customerRepository;
    }

    public Id createFor(String customerName) throws CustomerNotFound {
        Customer customer = customerRepository.getUserByName(customerName);
        if(customer == null)
            throw new CustomerNotFound();

        ShoppingCart cart = new ShoppingCart(new Id(), customer);

        shoppingCartRepository.create(cart);

        return cart.id;
    }
}
