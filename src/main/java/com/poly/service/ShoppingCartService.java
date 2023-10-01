package com.poly.service;

import com.poly.model.ShoppingCart;

import java.util.Collection;

public interface ShoppingCartService {
    public void add(ShoppingCart item, Integer soLuong);
    public void remove(int id);
    public int getAmout();
    public int getCount();
    public int getTotalMoneyOfOneProduct(int productId);
    public Collection<ShoppingCart> getAll();
    public void clear();
    public ShoppingCart update(int productId, int soLuong);
}
