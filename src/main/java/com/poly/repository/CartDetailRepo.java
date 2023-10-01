package com.poly.repository;

import com.poly.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CartDetailRepo extends JpaRepository<CartDetail,Integer> {
    @Query("SELECT c FROM CartDetail c WHERE c.cartId=?1")
    List<CartDetail> getCartDetail(Integer cartId);
    @Query("SELECT cd FROM CartDetail cd WHERE cd.cartId=?1 AND cd.productId=?2")
    CartDetail existByProductId(Integer cartId,Integer productId);
    @Modifying
    @Query("DELETE FROM CartDetail cd WHERE cd.cartId=?1 AND cd.productId=?2")
    void deleteByProductId(Integer cartId,Integer productId);

    @Modifying
    @Query("DELETE FROM CartDetail cd WHERE cd.cartId=?1")
    void deleteAllByCartId(Integer cartId);
}
