package com.upgrad.Eshop.services;

import com.upgrad.Eshop.dao.EshopShoppingAddressDAO;
import com.upgrad.Eshop.dto.ShippingAddressDTO;
import com.upgrad.Eshop.entities.EshopShoppingAddress;

public interface ShippingAddressService {

    public EshopShoppingAddress storeInAddressTable(EshopShoppingAddress eshopShoppingAddress);
    public Boolean checkZipcode(ShippingAddressDTO shippingAddressDTO);
    public Boolean checkPhoneNumber(ShippingAddressDTO shippingAddressDTO);
}
