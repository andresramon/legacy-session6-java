package com.cokaido.shoppingcart.controllers;

import com.cokaido.shoppingcart.appServices.useCases.CalculateCartPriceUseCase;
import com.cokaido.shoppingcart.appServices.useCases.exceptions.ShoppingCartNotFound;
import com.cokaido.shoppingcart.domain.base.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckOutController {


    private final CalculateCartPriceUseCase calculateCartPriceUseCase;

    @Autowired
    public CheckOutController(CalculateCartPriceUseCase calculateCartPriceUseCase) {
        this.calculateCartPriceUseCase = calculateCartPriceUseCase;
    }

    @GetMapping("/calculatePrice/{shoppingCartId}")
    public Double calculatePrice(@PathVariable Id shoppingCartId) throws ShoppingCartNotFound {
        return calculateCartPriceUseCase.calculateCartTotal(shoppingCartId);
    }
}
