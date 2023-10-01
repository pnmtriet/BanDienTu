package com.poly.service.impl;

import com.poly.model.ShoppingCart;
import com.poly.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    Map<Integer, ShoppingCart> maps=new HashMap<>();

    @Override
    public void add(ShoppingCart item,Integer soLuong) {
        int productId=item.getId();
        ShoppingCart cartItem=maps.get(productId);//truy vấn giỏ hàng theo mã sp
        //Nếu chưa tồn tại
        if(cartItem==null) {
            maps.put(productId, item);
        }else {
            cartItem.setSoLuong(cartItem.getSoLuong()+soLuong);
        }
    }

    @Override
    public void remove(int id) {
        maps.remove(id);
    }
    @Override
    public ShoppingCart update(int productId, int soLuong) {
        ShoppingCart cartItem=maps.get(productId);
        cartItem.setSoLuong(soLuong);
        return cartItem;
    }
    @Override
    public void clear() {
        maps.clear();
    }
    @Override
    public Collection<ShoppingCart> getAll(){
        return maps.values();
    }
    @Override
    public int getCount() {
        return maps.values().stream()
                .mapToInt(item->item.getSoLuong()).sum();
    }

    @Override
    public int getTotalMoneyOfOneProduct(int productId) {
        ShoppingCart cartItem=maps.get(productId);
        return cartItem.getSoLuong()*cartItem.getPrice().intValue();
    }

    @Override
    public int getAmout() {
        return maps.values().stream()
                .mapToInt(item -> {
                    return item.getPrice()!=null
                            ?item.getSoLuong()*(item.getPrice()*(100-item.getDiscount())/100)
                            :item.getSoLuong()*0;
                })
                .sum();
    }
}
