package com.upgrad.Eshop.services;

import com.upgrad.Eshop.dao.EshopShoppingAddressDAO;
import com.upgrad.Eshop.dto.ShippingAddressDTO;
import com.upgrad.Eshop.entities.EshopShoppingAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service(value = "shippingAddressService")
public class ShippingAddressServiceImpl implements ShippingAddressService{

    @Autowired
    EshopShoppingAddressDAO eshopShoppingAddressDAO;

    @Override
    public EshopShoppingAddress storeInAddressTable(EshopShoppingAddress eshopShoppingAddress) {
        return eshopShoppingAddressDAO.save(eshopShoppingAddress);
    }

    @Override
    public Boolean checkZipcode(ShippingAddressDTO shippingAddressDTO) {

        Matcher matcher;
        if (shippingAddressDTO.getZipCode().length() >= 6) {
            Pattern pattern = Pattern.compile( "[a-zA-Z]" );
            matcher = pattern.matcher(shippingAddressDTO.getZipCode());
        }
        else return false;
        return !matcher.find();
    }

    @Override
    public Boolean checkPhoneNumber(ShippingAddressDTO shippingAddressDTO) {
        Matcher matcher;
        if (shippingAddressDTO.getPhoneNumber().length() == 10) {
            Pattern pattern = Pattern.compile( "[a-zA-Z]" );
            matcher = pattern.matcher(shippingAddressDTO.getPhoneNumber());
        }
        else return false;
        return !matcher.find();
    }
}
