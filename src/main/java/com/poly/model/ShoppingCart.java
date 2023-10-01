package com.poly.model;

import com.poly.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCart extends Product {
    private int soLuong=1;

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
