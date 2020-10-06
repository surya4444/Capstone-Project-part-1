package com.upgrad.Eshop.services;

import com.upgrad.Eshop.entities.EshopUser;
import com.upgrad.Eshop.exception.UserNotFoundException;

public interface UserService {

    public EshopUser getUsersDetails(int id) throws UserNotFoundException;
    public String checkEmail(String email);
    public Boolean checkEmail2(String email);
    public String checkUserName(String username);
    public Boolean checkPhoneNumber(String phoneNumber);
    public Boolean checkPassword(String password);
    public EshopUser acceptUsersDetails(EshopUser eshopUser);

}
