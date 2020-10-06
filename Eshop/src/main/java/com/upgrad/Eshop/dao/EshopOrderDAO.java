package com.upgrad.Eshop.dao;

import com.upgrad.Eshop.entities.EshopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("eshopOrderDAO")
public interface EshopOrderDAO extends JpaRepository<EshopOrder, Integer> {
}
