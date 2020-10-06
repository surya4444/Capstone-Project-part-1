package com.upgrad.Eshop.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class EshopShoppingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private  String State;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String landmark;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String zipcode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    EshopUser eshopUser;

    @OneToMany(mappedBy = "eshopShoppingAddress")
    List<EshopOrder> eshopOrder;

}
