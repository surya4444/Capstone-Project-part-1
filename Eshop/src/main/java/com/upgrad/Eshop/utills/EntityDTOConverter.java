package com.upgrad.Eshop.utills;

import com.upgrad.Eshop.dao.EshopUserDAO;
import com.upgrad.Eshop.dto.GetDetailsDTO;
import com.upgrad.Eshop.dto.LoginDTO;
import com.upgrad.Eshop.dto.ShippingAddressDTO;
import com.upgrad.Eshop.dto.SignInDTO;
import com.upgrad.Eshop.entities.EshopShoppingAddress;
import com.upgrad.Eshop.entities.EshopUser;
import com.upgrad.Eshop.exception.UserNotFoundException;
import com.upgrad.Eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOConverter {

    @Autowired
    EshopUserDAO eshopUserDAO;

    @Autowired
    UserService userService;

    public LoginDTO convertToSignInDTO(EshopUser eshopUser){

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setId(eshopUser.getUser_id());
        loginDTO.setUserName(eshopUser.getUsername());
        loginDTO.setPassword(eshopUser.getPassword());
        loginDTO.setFirstName(eshopUser.getFirstName());
        loginDTO.setLastName(eshopUser.getLastName());
        loginDTO.setEmail(eshopUser.getEmail());
        loginDTO.setContactNumber(eshopUser.getPhoneNumber());
        loginDTO.setCreated(eshopUser.getCreated());
        loginDTO.setRole(eshopUser.getRole());
        loginDTO.setUpdated(eshopUser.getUpdated());

        return loginDTO;
    }

    public SignInDTO covertToLoginDTO(SignInDTO eshopUser){
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setUsername(eshopUser.getUsername());
        signInDTO.setMessage("success");
        return signInDTO;
    }

    public GetDetailsDTO forGetDetails(EshopUser eshopUser){
            GetDetailsDTO getDetails = new GetDetailsDTO();
            getDetails.setId(eshopUser.getUser_id());
            getDetails.setEmail(eshopUser.getEmail());
            getDetails.setPassword(eshopUser.getPassword());
            getDetails.setContactNumber(eshopUser.getPhoneNumber());
            getDetails.setFirstName(eshopUser.getFirstName());
            getDetails.setLastName(eshopUser.getLastName());
            getDetails.setCreated(eshopUser.getCreated());
            getDetails.setUpdated(eshopUser.getUpdated());
            getDetails.setUserName(eshopUser.getUsername());
            getDetails.setRole(eshopUser.getRole());

        return getDetails;
    }

    public ShippingAddressDTO convertToShippingAddressDTO(EshopShoppingAddress eshopShoppingAddress) throws UserNotFoundException {

        ShippingAddressDTO shippingAddressDTO = new ShippingAddressDTO();

        shippingAddressDTO.setId(eshopShoppingAddress.getId());
        shippingAddressDTO.setPhoneNumber(eshopShoppingAddress.getPhone());
        shippingAddressDTO.setName(eshopShoppingAddress.getName());
        shippingAddressDTO.setCity(eshopShoppingAddress.getCity());
        shippingAddressDTO.setLandmark(eshopShoppingAddress.getLandmark());
        shippingAddressDTO.setStreet(eshopShoppingAddress.getStreet());
        shippingAddressDTO.setState(eshopShoppingAddress.getState());
        shippingAddressDTO.setZipCode(eshopShoppingAddress.getZipcode());
        shippingAddressDTO.setUserId(eshopShoppingAddress.getEshopUser().getUser_id());
        return shippingAddressDTO;
    }
}