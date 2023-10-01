package com.poly.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders extends BaseEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "order_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @Column(name = "note", nullable = true, length = -1)
    private String note;

    @Column(name = "order_status", nullable = true, length = -1)
    private String orderStatus;

    @Column(name = "total_money", nullable = true, precision = 0)
    private BigDecimal totalMoney;

    @Column(name = "delivery_date", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    @Column(name = "account_id", nullable = true)
    private Integer accountId;

    @Column(name = "ship_detail_id", nullable = true)
    private Integer shipDetailId;

    @Column(name = "is_deleted", nullable = true)
    private Boolean isDeleted;

    @Column(name = "payment_method", nullable = true, length = -1)
    private String paymentMethod;

    @Column(name = "payment_status", nullable = true, length = -1)
    private String paymentStatus;

    @Column(name = "ship_method", nullable = true, length = -1)
    private String shipMethod;

    @Column(name = "ship_method_id", nullable = true)
    private Integer shipMethodId;

    @Column(name = "delivery_charges", nullable = true)
    private Integer deliveryCharges;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Account accountByAccountId;

    @ManyToOne
    @JoinColumn(name = "ship_detail_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ShipDetail shipDetailByShipDetailId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getShipDetailId() {
        return shipDetailId;
    }

    public void setShipDetailId(Integer shipDetailId) {
        this.shipDetailId = shipDetailId;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getShipMethod() {
        return shipMethod;
    }

    public void setShipMethod(String shipMethod) {
        this.shipMethod = shipMethod;
    }

    public Integer getShipMethodId() {
        return shipMethodId;
    }

    public void setShipMethodId(Integer shipMethodId) {
        this.shipMethodId = shipMethodId;
    }

    public Integer getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(Integer deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public Account getAccountByAccountId() {
        return accountByAccountId;
    }

    public void setAccountByAccountId(Account accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
    }

    public ShipDetail getShipDetailByShipDetailId() {
        return shipDetailByShipDetailId;
    }

    public void setShipDetailByShipDetailId(ShipDetail shipDetailByShipDetailId) {
        this.shipDetailByShipDetailId = shipDetailByShipDetailId;
    }
}
