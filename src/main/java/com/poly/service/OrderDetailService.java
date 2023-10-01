package com.poly.service;

import com.poly.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getAll(Integer orderId);
    OrderDetail save(OrderDetail orderDetailDataModel);
}
