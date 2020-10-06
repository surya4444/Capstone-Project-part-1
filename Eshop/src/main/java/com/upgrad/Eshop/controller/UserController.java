package com.upgrad.Eshop.controller;

import com.upgrad.Eshop.dao.EshopProductDAO;
import com.upgrad.Eshop.dao.EshopUserDAO;
import com.upgrad.Eshop.dto.GetDetailsDTO;
import com.upgrad.Eshop.entities.EshopUser;
import com.upgrad.Eshop.response.CustomResponse;
import com.upgrad.Eshop.utills.DTOEntityConverter;
import com.upgrad.Eshop.utills.EntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {

    @Autowired
    EshopUserDAO eshopUserDAO;

    @Autowired
    EshopProductDAO eshopProductDAO;

    @Autowired
    EntityDTOConverter entityDTOConverter;

    @Autowired
    DTOEntityConverter dtoEntityConverter;

    @GetMapping("/users/details")
    public Object getDetails(@RequestBody GetDetailsDTO getDetails) {

//        Optional<EshopUser> getDetails1 = eshopUserDAO.findById(getDetails.getId());
        EshopUser getDetails1 = eshopUserDAO.findByTableId(getDetails.getId());

        if (getDetails1 == null){
            CustomResponse response = new CustomResponse(new Date(), "Please Login first to access this endpoint or the id is wrong", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
//        dtoEntityConverter.

        GetDetailsDTO responseEntity = entityDTOConverter.forGetDetails(getDetails1);
        return responseEntity;
    }

}
