package com.poly.service;
import com.poly.entity.Cart;

public interface CartService {
    Cart save(Cart cart);

    Boolean deleteById(Integer cartId);
}
