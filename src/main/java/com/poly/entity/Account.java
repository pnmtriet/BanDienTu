package com.poly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends BaseEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    
    @Column(name = "username", nullable = true, length = 20)
    private String username;
    
    @Column(name = "password", nullable = true, length = -1)
    private String password;

    @Column(name = "email", nullable = true, length = 255)
    private String email;

    @Column(name = "date_of_birth", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    
    @Column(name = "full_name", nullable = true, length = 50)
    private String fullName;
    
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;

    @Column(name = "gender", nullable = true,length = 10)
    private String gender;
    
    @Column(name = "cart_id", nullable = true)
    private Integer cartId;
    
    @Column(name = "is_deleted", nullable = true)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Cart cartByCartId;
}
