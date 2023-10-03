package com.poly.model;

import com.poly.entity.Product;

public class ProductResult extends Product {
    int priceSale;

    public int getPriceSale() {
        return (int) (getPrice() * (1 - getDiscount()/(double)100));
    }

    public void setPriceSale(int priceSale) {
        this.priceSale = priceSale;
    }

}
