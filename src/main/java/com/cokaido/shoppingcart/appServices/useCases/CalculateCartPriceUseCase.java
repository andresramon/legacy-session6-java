package com.cokaido.shoppingcart.appServices.useCases;

import com.cokaido.shoppingcart.appServices.data.CartProduct;
import com.cokaido.shoppingcart.appServices.interfaces.ICartQueries;
import com.cokaido.shoppingcart.appServices.useCases.exceptions.ShoppingCartNotFound;
import com.cokaido.shoppingcart.domain.base.Id;
import com.cokaido.shoppingcart.domain.services.ICartPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculateCartPriceUseCase {

    private final ICartPriceService priceAppService;
    private final ICartQueries cartQueries;

    @Autowired
    public CalculateCartPriceUseCase(ICartPriceService priceAppService, ICartQueries cartQueries) {
        this.priceAppService = priceAppService;
        this.cartQueries = cartQueries;
    }

    public Double calculateCartTotal(Id shoppingCartId) throws ShoppingCartNotFound {
        List<CartProduct> products = cartQueries.getProductsFromCart(shoppingCartId);

        if(products.isEmpty())
            throw new ShoppingCartNotFound();

        return priceAppService.calculateCart(products);
    }
}
