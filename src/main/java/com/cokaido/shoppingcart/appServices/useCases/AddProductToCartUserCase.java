package com.cokaido.shoppingcart.appServices.useCases;

import com.cokaido.shoppingcart.appServices.interfaces.IProductRepository;
import com.cokaido.shoppingcart.appServices.interfaces.IShoppingCartRepository;
import com.cokaido.shoppingcart.appServices.useCases.exceptions.ProductNotFound;
import com.cokaido.shoppingcart.appServices.useCases.exceptions.ShoppingCartNotFound;
import com.cokaido.shoppingcart.domain.ShoppingCart;
import com.cokaido.shoppingcart.domain.base.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddProductToCartUserCase {

    private IShoppingCartRepository cartRepository;
    private IProductRepository productRepository;

    @Autowired
    public AddProductToCartUserCase(IShoppingCartRepository cartRepository, IProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public void addProduct(Id cartId, String productCode) throws ShoppingCartNotFound, ProductNotFound {

        ShoppingCart cart = cartRepository.get(cartId);
        if( cart == null )
        {
            throw new ShoppingCartNotFound();
        }

        var product = productRepository.getByCode(productCode);
        if (product == null)
        {
            throw new ProductNotFound();
        }

        cart.addProduct(product);

        cartRepository.save(cart);

    }
}
