package com.upgrad.Eshop.dao;

import com.upgrad.Eshop.entities.EshopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("eshopUserDAO")
public interface EshopUserDAO extends JpaRepository<EshopUser,Integer> {

    EshopUser findByEmail(String email);
    EshopUser findByPhoneNumber(String mobileNumber);
    EshopUser findByUsername(String username);

    @Query("From EshopUser u Where u.id = :id")
    EshopUser findByTableId(@Param("id") int id);


}
