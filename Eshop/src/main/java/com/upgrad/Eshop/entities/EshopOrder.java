package com.upgrad.Eshop.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
public class EshopOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    EshopUser eshopUser;

    @ManyToOne
    EshopProduct eshopProduct;

    @ManyToOne
    EshopShoppingAddress eshopShoppingAddress;

    @Column(nullable = false)
    private Date order_date;

    @Column(nullable = false)
    private float amount;




}
