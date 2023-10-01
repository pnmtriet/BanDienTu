package com.poly.service.impl;

import com.poly.entity.Cart;
import com.poly.repository.CartRepo;
import com.poly.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepo cartRepo;

    @Override
    public Cart save(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public Boolean deleteById(Integer cartId) {
        Cart cart=cartRepo.findById(cartId).get();
        if(cart!=null){
            cart.setDeleted(true);
            cartRepo.save(cart);
            return true;
        }else{
            return false;
        }
    }
}
