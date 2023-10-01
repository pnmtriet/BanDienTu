package com.poly.service;

import com.poly.entity.CartDetail;

import java.util.List;

public interface CartDetailService {
    List<CartDetail> getCartDetail(Integer cartId);
    CartDetail save(CartDetail cartDetailDataModel);
    CartDetail existByProductId(Integer cartId,Integer productId);
    void deleteByProductId(Integer cartId,Integer productId);
    void deleteAllByCartId(Integer cartId);
}
