package com.poly.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="cart")
public class Cart extends BaseEntity implements Serializable {
    public Cart() {

    }
    public Cart(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "is_deleted", nullable = true)
    private Boolean isDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }


}
