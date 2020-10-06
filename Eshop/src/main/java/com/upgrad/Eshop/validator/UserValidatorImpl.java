package com.upgrad.Eshop.validator;

import com.upgrad.Eshop.dto.GetDetailsDTO;
import com.upgrad.Eshop.dto.LoginDTO;
import com.upgrad.Eshop.dto.ShippingAddressDTO;
import com.upgrad.Eshop.dto.SignInDTO;
import com.upgrad.Eshop.exception.APIException;
import org.springframework.stereotype.Service;

@Service
public class UserValidatorImpl implements UserValidator{

    @Override
    public void userLoginValidator(LoginDTO loginDTO) throws APIException {

        if (loginDTO.getFirstName() == null || loginDTO.getFirstName().isEmpty())
            throw new APIException("First name should not be null or empty");
        if (loginDTO.getContactNumber() == null || loginDTO.getContactNumber().isEmpty())
            throw new APIException("Contact number should not be null or empty");
        if (loginDTO.getEmail() == null || loginDTO.getContactNumber().isEmpty())
            throw new APIException("Contact number should not be null or empty");
        if (loginDTO.getUserName() == null || loginDTO.getUserName().isEmpty())
            throw new APIException("UserName should not be null or empty");
        if (loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty())
            throw new APIException("Password should not be null or empty");
    }

    @Override
    public void userSignInValidator(SignInDTO signInDTO) throws APIException {
        if (signInDTO.getUsername() == null || signInDTO.getUsername().isEmpty())
            throw new APIException("UserName Should not empty or null");
        if (signInDTO.getPassword() == null || signInDTO.getPassword().isEmpty())
            throw new APIException("password Should not empty or null");
    }

    @Override
    public void shippingAddressValidator(ShippingAddressDTO shippingAddressDTO) throws APIException {
        if (shippingAddressDTO.getCity() == null || shippingAddressDTO.getCity().isEmpty())
            throw new APIException("City name Should not empty or null");
        if (shippingAddressDTO.getLandmark() == null || shippingAddressDTO.getLandmark().isEmpty())
            throw new APIException("Landmark should not be null or empty");
        if (shippingAddressDTO.getName() == null || shippingAddressDTO.getName().isEmpty())
            throw new APIException("name should not be null or empty");
        if (shippingAddressDTO.getPhoneNumber() == null || shippingAddressDTO.getPhoneNumber().isEmpty())
            throw new APIException("phone number should not be null or empty");
        if (shippingAddressDTO.getState() == null || shippingAddressDTO.getState().isEmpty())
            throw new APIException("state should not be null or empty");
        if (shippingAddressDTO.getZipCode() == null || shippingAddressDTO.getZipCode().isEmpty())
            throw new APIException("zipcode should not be null or empty");
        if (shippingAddressDTO.getStreet() == null || shippingAddressDTO.getStreet().isEmpty())
            throw new APIException("street should not be null or empty");

    }

}
