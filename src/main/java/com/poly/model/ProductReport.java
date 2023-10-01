package com.poly.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ProductReport {
    @Id
    private Integer id;
    private String productName;
    private String images;
    private Integer numberOfSale;
    private Integer totalMoney;
}
