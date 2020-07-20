package org.fasttrackit.onlineshop.service;

import org.fasttrackit.onlineshop.domain.Cart;
import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.domain.User;
import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.persistence.CartRepository;
import org.fasttrackit.onlineshop.transfer.cart.AddProductsToCartRequest;
import org.fasttrackit.onlineshop.transfer.cart.CartResponse;
import org.fasttrackit.onlineshop.transfer.cart.ProductInCartResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public CartService(CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Transactional
    public void addProductsToCart(long cartId, AddProductsToCartRequest request) {
        LOGGER.info("Adding products to cart {}:{}", cartId, request);

        Cart cart = cartRepository.findById(cartId)
                .orElse(new Cart());

        if (cart.getUser() == null) {
            User user = userService.getUser(cartId);

            cart.setUser(user);
        }

        for (Long productId : request.getProductsIds()) {
            Product product = productService.getProduct(productId);

            cart.addProduct(product);

        }
        cartRepository.save(cart);
    }

    //using DTO's to avoid Lazy Loading Exceptions
    @Transactional
    public CartResponse getCart (long id) {
        LOGGER.info("Retrieving cart {}" , id);

        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException ("Cart" + id + "does not exist"));

        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(cart.getId());

        List<ProductInCartResponse> productsDtos = new ArrayList<>();

        for (Product product: cart.getProducts()) {
            ProductInCartResponse productsResponse = new ProductInCartResponse();
            productsResponse.setId(product.getId());
            productsResponse.setName(product.getName());
            productsResponse.setPrice(product.getPrice());
            productsResponse.setImageUrl(product.getImageUrl());

            productsDtos.add(productsResponse);
        }

        cartResponse.setProducts(productsDtos);

        return cartResponse;
    }


}
