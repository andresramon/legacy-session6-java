package com.cokaido.shoppingcart.acceptance;

import com.cokaido.shoppingcart.appServices.data.CartProduct;
import com.cokaido.shoppingcart.appServices.interfaces.ICartQueries;
import com.cokaido.shoppingcart.appServices.interfaces.ICustomerRepository;
import com.cokaido.shoppingcart.appServices.interfaces.IProductRepository;
import com.cokaido.shoppingcart.appServices.interfaces.IShoppingCartRepository;
import com.cokaido.shoppingcart.appServices.useCases.AddProductToCartUserCase;
import com.cokaido.shoppingcart.appServices.useCases.CalculateCartPriceUseCase;
import com.cokaido.shoppingcart.appServices.useCases.CreateEmptyCartUseCase;
import com.cokaido.shoppingcart.appServices.useCases.exceptions.CustomerNotFound;
import com.cokaido.shoppingcart.appServices.useCases.exceptions.ProductNotFound;
import com.cokaido.shoppingcart.appServices.useCases.exceptions.ShoppingCartNotFound;
import com.cokaido.shoppingcart.domain.Customer;
import com.cokaido.shoppingcart.domain.Product;
import com.cokaido.shoppingcart.domain.ShoppingCart;
import com.cokaido.shoppingcart.domain.base.Id;
import com.cokaido.shoppingcart.domain.data.ProductData;
import com.cokaido.shoppingcart.domain.services.CartPriceService;
import com.cokaido.shoppingcart.domain.services.ICartPriceService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CalculatePriceShoppingCartWithProductTest {


    @Test
    public void calculatePriceShoppingCart() throws CustomerNotFound, ShoppingCartNotFound, ProductNotFound {
        String customerName = "Juanito";
        String productCode = "VOUCHER";
        Id id = new Id();
        Customer customer = new Customer(id, customerName);
        IShoppingCartRepository iShoppingCartRepository = mock(IShoppingCartRepository.class);

        ShoppingCart cart = createEmptyShoppingCart(customerName, customer, iShoppingCartRepository);

        ProductData productData = addProductToShoppingCart(productCode, id, iShoppingCartRepository, cart);
        List<CartProduct> cartProducts = Arrays.asList(new CartProduct(productData.code, productData.price));

        calculateCartPrice(cart, cartProducts);

    }

    private void calculateCartPrice(ShoppingCart cart, List<CartProduct> cartProducts) throws ShoppingCartNotFound {
        ICartQueries iCartQueries = mock(ICartQueries.class);
        when(iCartQueries.getProductsFromCart(cart.id)).thenReturn(cartProducts);
        ICartPriceService iCartPriceService = new CartPriceService();
        CalculateCartPriceUseCase calculateCartPriceUseCase = new CalculateCartPriceUseCase(iCartPriceService, iCartQueries);
        Double cartPrice = calculateCartPriceUseCase.calculateCartTotal(cart.id);

        verify(iCartQueries).getProductsFromCart(cart.id);
        Assertions.assertEquals(5.00, cartPrice);
    }

    private ProductData addProductToShoppingCart(String productCode, Id id, IShoppingCartRepository iShoppingCartRepository, ShoppingCart cart) throws ShoppingCartNotFound, ProductNotFound {
        when(iShoppingCartRepository.get(cart.id)).thenReturn(cart);
        IProductRepository iProductRepository = mock(IProductRepository.class);
        when(iProductRepository.getByCode(productCode)).thenReturn(new Product(id, productCode, 5.00));
        ArgumentCaptor<ShoppingCart> shoppingCartArgumentCaptor = ArgumentCaptor.forClass(ShoppingCart.class);
        AddProductToCartUserCase addProductToCartUserCase = new AddProductToCartUserCase(iShoppingCartRepository, iProductRepository);
        addProductToCartUserCase.addProduct(cart.id, productCode);

        verify(iProductRepository).getByCode(productCode);
        verify(iShoppingCartRepository).save(shoppingCartArgumentCaptor.capture());
        ProductData product = shoppingCartArgumentCaptor.getValue().toData().products.iterator().next();
        Assertions.assertEquals(product.code, productCode);

        return product;
    }

    private ShoppingCart createEmptyShoppingCart(String customerName, Customer customer, IShoppingCartRepository iShoppingCartRepository) throws CustomerNotFound {
        ICustomerRepository customerRepository = mock(ICustomerRepository.class);
        when(customerRepository.getUserByName(customerName)).thenReturn(customer);
        CreateEmptyCartUseCase emptyCartUseCase = new CreateEmptyCartUseCase(iShoppingCartRepository, customerRepository);
        Id shoppingCartId = emptyCartUseCase.createFor(customerName);
        ArgumentCaptor<ShoppingCart> shoppingCartArgumentCaptor = ArgumentCaptor.forClass(ShoppingCart.class);

        verify(iShoppingCartRepository).create(shoppingCartArgumentCaptor.capture());
        ShoppingCart cart = shoppingCartArgumentCaptor.getValue();
        Assertions.assertEquals(cart.toData().id, shoppingCartId);
        Assertions.assertEquals(cart.toData().customer.name, customerName);
        return cart;
    }

}
