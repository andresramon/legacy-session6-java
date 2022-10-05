package com.cokaido.shoppingcart.controllers;

import com.cokaido.shoppingcart.appServices.useCases.AddProductToCartUserCase;
import com.cokaido.shoppingcart.appServices.useCases.CreateEmptyCartUseCase;
import com.cokaido.shoppingcart.appServices.useCases.exceptions.CustomerNotFound;
import com.cokaido.shoppingcart.appServices.useCases.exceptions.ProductNotFound;
import com.cokaido.shoppingcart.appServices.useCases.exceptions.ShoppingCartNotFound;
import com.cokaido.shoppingcart.domain.base.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingCartController {

    private CreateEmptyCartUseCase emptyCartUseCase;
    private AddProductToCartUserCase addProductToCartUserCase;

    @Autowired
    public ShoppingCartController(CreateEmptyCartUseCase emptyCartUseCase, AddProductToCartUserCase addProductToCartUserCase) {
        this.emptyCartUseCase = emptyCartUseCase;
        this.addProductToCartUserCase = addProductToCartUserCase;
    }



    @PostMapping("/createNewShoppingCart/{customerName}")
    Id createNewShoppingCart(@PathVariable String customerName) throws CustomerNotFound {

     return emptyCartUseCase.createFor(customerName);
    }

    @PutMapping("/shoppingCarts/{id}/addProduct/{productCode}")
    void addProduct(@PathVariable Id id,@PathVariable String productCode) throws ShoppingCartNotFound, ProductNotFound {

        addProductToCartUserCase.addProduct(id,productCode);
    }
}
