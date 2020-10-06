package com.upgrad.Eshop.dao;

import com.upgrad.Eshop.entities.EshopShoppingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("eshopShoppingAddressDAO")
public interface EshopShoppingAddressDAO extends JpaRepository<EshopShoppingAddress, Integer> {
}
