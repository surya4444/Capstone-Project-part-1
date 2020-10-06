package com.upgrad.Eshop.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class EshopProduct {


    @Column(nullable = false)
    private int available_items;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false , columnDefinition = "VARCHAR(1000)")
    private String description;

    @Column( name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String updated;

    @OneToMany(mappedBy = "eshopProduct")
    List<EshopOrder> eshopOrder;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int product_id;

}

//available_items, category, created, description, image_url, manufacturer, name, price, updated, product_id