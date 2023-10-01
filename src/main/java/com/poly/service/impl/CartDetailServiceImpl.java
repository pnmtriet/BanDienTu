package com.poly.service.impl;

import com.poly.entity.CartDetail;
import com.poly.repository.CartDetailRepo;
import com.poly.service.CartDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    CartDetailRepo cartDetailRepo;


    @Override
    public List<CartDetail> getCartDetail(Integer cartId) {
        return cartDetailRepo.getCartDetail(cartId);
    }

    @Override
    public CartDetail save(CartDetail cartDetailDataModel) {
        CartDetail cartDetail=cartDetailRepo.existByProductId(cartDetailDataModel.getCartId(),cartDetailDataModel.getProductId());
        if(cartDetail==null){
            CartDetail resultSaved= cartDetailRepo.save(cartDetailDataModel);
            return resultSaved;
        }else{
            cartDetail.setAmount(cartDetail.getAmount()+cartDetailDataModel.getAmount());
            CartDetail resultSaved= cartDetailRepo.save(cartDetail);
            return resultSaved;
        }
    }

    @Override
    public CartDetail existByProductId(Integer cartId, Integer productId) {
        return cartDetailRepo.existByProductId(cartId,productId);
    }

    @Override
    public void deleteByProductId(Integer cartId,Integer productId) {
        cartDetailRepo.deleteByProductId(cartId,productId);
    }

    @Override
    public void deleteAllByCartId(Integer cartId) {
        cartDetailRepo.deleteAllByCartId(cartId);
    }
}
