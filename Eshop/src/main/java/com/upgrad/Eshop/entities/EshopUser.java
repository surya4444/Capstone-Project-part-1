package com.upgrad.Eshop.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class EshopUser {

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private String email;

    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @Column(name =  "last_name")
    private String lastName;

    @Column(nullable = false)
    private String Password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Date updated;

    @Column(name = "user_name",nullable = false)
    private String username;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int user_id;

    @OneToMany(mappedBy = "eshopUser")
    List<EshopOrder> eshopOrder;

    @OneToMany(mappedBy = "eshopUser")
    List<EshopShoppingAddress> eshopShoppingAddress;

    public EshopUser() {
    }

}
//created, email, first_name, last_name, password, phone_number, role, updated, user_name, id