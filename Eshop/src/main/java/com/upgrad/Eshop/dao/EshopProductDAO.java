package com.upgrad.Eshop.dao;

import com.upgrad.Eshop.entities.EshopProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("eshopProductDAO")
public interface EshopProductDAO extends JpaRepository<EshopProduct, Integer> {
}
