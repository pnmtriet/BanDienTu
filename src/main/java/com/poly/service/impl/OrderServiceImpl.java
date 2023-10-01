package com.poly.service.impl;

import com.poly.entity.Orders;
import com.poly.repository.OrderRepo;
import com.poly.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;

    @Override
    public List<Orders> findAllAdmin(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Orders> pageOrders=orderRepo.findAll(pageable);
        List<Orders> list=pageOrders.getContent();
        return list;
    }

    @Override
    public List<Orders> findOrdersExist(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Orders> pageOrders=orderRepo.findOrdersExist(pageable);
        List<Orders> list=pageOrders.getContent();
        return list;
    }

    @Override
    public Orders findById(Integer orderId) {
        return orderRepo.findById(orderId).get();
    }

    @Override
    public boolean existsById(Integer id) {
        return orderRepo.existsById(id);
    }

    @Override
    public Orders save(Orders orders) {
        return orderRepo.save(orders);
    }

}
