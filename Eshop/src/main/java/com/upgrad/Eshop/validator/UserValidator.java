package com.upgrad.Eshop.validator;

import com.upgrad.Eshop.dto.GetDetailsDTO;
import com.upgrad.Eshop.dto.LoginDTO;
import com.upgrad.Eshop.dto.ShippingAddressDTO;
import com.upgrad.Eshop.dto.SignInDTO;
import com.upgrad.Eshop.exception.APIException;

public interface UserValidator {

    public void userLoginValidator(LoginDTO loginDTO) throws APIException;
    public void userSignInValidator(SignInDTO signInDTO) throws APIException;
    public void shippingAddressValidator(ShippingAddressDTO shippingAddressDTO) throws APIException;
}
