package com.upgrad.Eshop.utills;


import com.upgrad.Eshop.config.encrypt;
import com.upgrad.Eshop.dao.EshopShoppingAddressDAO;
import com.upgrad.Eshop.dto.LoginDTO;
import com.upgrad.Eshop.dto.ShippingAddressDTO;
import com.upgrad.Eshop.dto.SignInDTO;
import com.upgrad.Eshop.entities.EshopShoppingAddress;
import com.upgrad.Eshop.entities.EshopUser;
import com.upgrad.Eshop.exception.UserNotFoundException;
import com.upgrad.Eshop.services.ShippingAddressService;
import com.upgrad.Eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.security.auth.kerberos.EncryptionKey;

@Component
public class DTOEntityConverter
{

    @Autowired
    UserService userService;

    public EshopUser convertToSignInEntity(LoginDTO loginDTO){
        EshopUser eshopUser = new EshopUser();

        eshopUser.setUsername(loginDTO.getUserName());
        /**
         * Here bcrypt the password and store in database.
         * using class name as encrypt in config package
         */
        String encryptedString = encrypt.encrypt(loginDTO.getPassword()) ;
        eshopUser.setPassword(encryptedString);

        eshopUser.setFirstName(loginDTO.getFirstName());
        eshopUser.setLastName(loginDTO.getLastName());
        eshopUser.setEmail(loginDTO.getEmail());
        eshopUser.setPhoneNumber(loginDTO.getContactNumber());
        return eshopUser;
    }

    public EshopUser convertLogInEntity(SignInDTO signInDTO){

        EshopUser eshopUser = new EshopUser();

        eshopUser.setUsername(signInDTO.getUsername());
        signInDTO.setMessage("success");
        return eshopUser;
    }

    public EshopShoppingAddress convertToShippingAddressEntity(ShippingAddressDTO shippingAddressDTO) throws UserNotFoundException {
        EshopShoppingAddress eshopShoppingAddress = new EshopShoppingAddress();

        eshopShoppingAddress.setName(shippingAddressDTO.getName());
        eshopShoppingAddress.setPhone(shippingAddressDTO.getPhoneNumber());
        eshopShoppingAddress.setCity(shippingAddressDTO.getCity());
        eshopShoppingAddress.setLandmark(shippingAddressDTO.getLandmark());
        eshopShoppingAddress.setStreet(shippingAddressDTO.getStreet());
        eshopShoppingAddress.setState(shippingAddressDTO.getState());
        eshopShoppingAddress.setZipcode(shippingAddressDTO.getZipCode());
        eshopShoppingAddress.setEshopUser(userService.getUsersDetails(shippingAddressDTO.getUserId()));
        return eshopShoppingAddress;
    }
}