package com.poly.repository;
import com.poly.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepo extends JpaRepository<OrderDetail,Integer> {
    @Query("SELECT od FROM OrderDetail od WHERE od.orderId=?1 AND od.ordersByOrderId.isDeleted=FALSE")
    List<OrderDetail> findAllByOrderId(Integer orderId);
    @Query("SELECT od FROM OrderDetail od WHERE od.orderId=?1")
    List<OrderDetail> findAllByOrderIdAdmin(Integer orderId);

    @Query("SELECT od FROM OrderDetail od WHERE od.ordersByOrderId.accountId=?1 " +
            "AND od.ordersByOrderId.isDeleted=FALSE " +
            "AND od.ordersByOrderId.id=?2" +
            "AND od.ordersByOrderId.orderStatus like %?3%")
    List<OrderDetail> findAllByAccountIdAndOrderIdAndOrderStatus(Integer accountId, Integer orderId, String orderStatus);
    @Query("SELECT od FROM OrderDetail od WHERE od.ordersByOrderId.accountId=?1 " +
            "AND od.ordersByOrderId.isDeleted=FALSE " +
            "AND od.ordersByOrderId.orderStatus like %?2%")
    List<OrderDetail> findAllByAccountIdAndOrderStatus(Integer accountId, String orderStatus);

}
