package com.poly.service.impl;
import com.poly.entity.OrderDetail;
import com.poly.service.OrderDetailService;
import com.poly.repository.OrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepo orderDetailRepo;
    @Override
    public List<OrderDetail> getAll(Integer orderId) {
        return orderDetailRepo.findAll();
    }

    @Override
    public OrderDetail save(OrderDetail orderDetailDataModel) {
        return orderDetailRepo.save(orderDetailDataModel);
    }
}
