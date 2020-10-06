package com.upgrad.Eshop.controller;

import com.upgrad.Eshop.dto.ShippingAddressDTO;
import com.upgrad.Eshop.entities.EshopShoppingAddress;
import com.upgrad.Eshop.entities.EshopUser;
import com.upgrad.Eshop.exception.APIException;
import com.upgrad.Eshop.exception.UserNotFoundException;
import com.upgrad.Eshop.response.CustomResponse;
import com.upgrad.Eshop.services.ShippingAddressService;
import com.upgrad.Eshop.services.UserService;
import com.upgrad.Eshop.utills.DTOEntityConverter;
import com.upgrad.Eshop.utills.EntityDTOConverter;
import com.upgrad.Eshop.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ShippingAddressController {

    @Autowired
    DTOEntityConverter dtoEntityConverter;

    @Autowired
    EntityDTOConverter entityDTOConverter;

    @Autowired
    UserValidator userValidator;

    @Autowired
    ShippingAddressService shippingAddressService;

    @Autowired
    UserService userService;

    @PostMapping(value = "/user-addresses" , consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity shippingAddress(@RequestBody ShippingAddressDTO shippingAddressDTO) throws APIException, UserNotFoundException {

        userValidator.shippingAddressValidator(shippingAddressDTO);
        ResponseEntity responseEntity = null;

        EshopUser user = userService.getUsersDetails(shippingAddressDTO.getUserId());
        if (!(user.getRole()).equals("user")){
            CustomResponse response = new CustomResponse(new Date(), "You are not authorised to access this endpoint.", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        Boolean result = shippingAddressService.checkZipcode(shippingAddressDTO);
        if (!result){
            CustomResponse response = new CustomResponse(new Date(), "Invalid zip code!", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        Boolean result1 = shippingAddressService.checkPhoneNumber(shippingAddressDTO);
        if (!result1){
            CustomResponse response = new CustomResponse(new Date(), "Invalid contact number!", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        try {
            EshopShoppingAddress newAddress = dtoEntityConverter.convertToShippingAddressEntity(shippingAddressDTO);
            EshopShoppingAddress storeUser = shippingAddressService.storeInAddressTable(newAddress);
            ShippingAddressDTO responseEntity1 = entityDTOConverter.convertToShippingAddressDTO(storeUser);
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(responseEntity1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseEntity;
    }

}
