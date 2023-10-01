package com.poly.service;

import com.poly.entity.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> findAllAdmin(Integer page, Integer size);
    List<Orders> findOrdersExist(Integer page, Integer size);
    Orders findById(Integer orderId);
    boolean existsById(Integer id);
    Orders save(Orders orderDataModel);
}
