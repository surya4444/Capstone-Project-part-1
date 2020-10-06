package com.upgrad.Eshop.controller;

import com.upgrad.Eshop.config.decrypt;
import com.upgrad.Eshop.dao.EshopUserDAO;
import com.upgrad.Eshop.dto.LoginDTO;
import com.upgrad.Eshop.dto.SignInDTO;
import com.upgrad.Eshop.entities.EshopUser;
import com.upgrad.Eshop.response.CustomResponse;
import com.upgrad.Eshop.exception.APIException;
import com.upgrad.Eshop.security.JwtTokenProvider;
import com.upgrad.Eshop.services.UserService;
import com.upgrad.Eshop.utills.DTOEntityConverter;
import com.upgrad.Eshop.utills.EntityDTOConverter;
import com.upgrad.Eshop.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;

@RestController
public class AuthController {

    @Autowired
    DTOEntityConverter dtoEntityConverter;

    @Autowired
    EntityDTOConverter entityDTOConverter;

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @Qualifier("eshopUserDAO")
    @Autowired
    EshopUserDAO eshopUserDAO;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    /**
     * If you use table that are already create in oracle we can't retrive data that are store by sql quary
     * if you store row by using spring boot we can retrive that data
     */


    @PostMapping(value = "auth/users" , consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity newUser(@RequestBody LoginDTO loginDTO) throws APIException {

        ResponseEntity responseEntity = null;

        userValidator.userLoginValidator(loginDTO);//It check That give input with null or empty throw exception

        /**
         * Here we check that give data from postman username already exit in table are not
         * If exist throw json message that "Try any other username, this username is already registered!"
         *
         * business logic in userServiceImpl in service folder
         */
        String username = userService.checkUserName(loginDTO.getUserName());
        if (username == null){
            CustomResponse response = new CustomResponse(new Date(), "Try any other username, this username is already registered!", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        /**
         * Here we check that give data from postman email already exit in table are not
         * If exist throw json message that "Try any other email address, this email address is already registered!"
         *
         * business logic in userServiceImpl in service folder
         */
        String email = userService.checkEmail(loginDTO.getEmail());
        if (email == null){
            CustomResponse response = new CustomResponse(new Date(), "Try any other email address, this email address is already registered!", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        /**
         * Here we check that give data from postman email is in correct format are not
         * If not throw json message that "Invalid email-id format!"
         *
         * business logic in userServiceImpl in service folder
         */
        Boolean email2 = userService.checkEmail2(loginDTO.getEmail());
        if (!email2){
            CustomResponse response = new CustomResponse(new Date(), "Invalid email-id format!", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        /**
         * Here we check that give data from postman contact number are in 10 digits are not and also check that
         * phone number contain any character throw json message with "Invalid contact number!"
         *
         * business logic in userServiceImpl in service folder
         */
        Boolean phoneNumber = userService.checkPhoneNumber(loginDTO.getContactNumber());
        if (!phoneNumber){
            CustomResponse response = new CustomResponse(new Date(), "Invalid contact number!", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        /**
         * Here we check that give data from postman password are atleast 8 are not
         * if not throw json message with "Weak password!"
         *
         * business logic in userServiceImpl in service folder
         */
        Boolean password = userService.checkPassword(loginDTO.getPassword());
        if (!password){
            CustomResponse response = new CustomResponse(new Date(), "Weak password!", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        try {
            EshopUser newUsers = dtoEntityConverter.convertToSignInEntity(loginDTO);//First we have to convert dto to entity to store in table
            EshopUser storeUser = userService.acceptUsersDetails(newUsers);//store in table logic in userService
            LoginDTO responseUserDTO = entityDTOConverter.convertToSignInDTO(storeUser);//to display the responce we have to convert entity to dto
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(responseUserDTO);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @PostMapping(value = "users/access_token" , consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity SignIn(@RequestBody SignInDTO signInDTO) throws APIException {
        ResponseEntity responseEntity = null;
        userValidator.userSignInValidator(signInDTO);
        /**
         * the data from postman ,username already exist are not if exist store in userName else store null
         * in userName
         */
        EshopUser userName = eshopUserDAO.findByUsername(signInDTO.getUsername());

//        String refreshToken = jwtTokenProvider.createRefreshToken(userName.getEmail());
        String token = jwtTokenProvider.createToken(signInDTO.getUsername());
        if (userName == null){
            CustomResponse response = new CustomResponse(new Date(), "This username has not been registered!", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        String decryptedString = decrypt.decrypt(userName.getPassword());
        if (!(decryptedString).equals(signInDTO.getPassword())){
            CustomResponse response = new CustomResponse(new Date(), "Invalid Credentials!", 400);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        try {
            SignInDTO eshopUser = entityDTOConverter.covertToLoginDTO(signInDTO);
            eshopUser.setPassword(decryptedString);
            eshopUser.setToken(token);
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(eshopUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseEntity;
    }
}
