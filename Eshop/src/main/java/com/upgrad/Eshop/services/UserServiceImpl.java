package com.upgrad.Eshop.services;

import com.upgrad.Eshop.dao.EshopUserDAO;
import com.upgrad.Eshop.entities.EshopUser;
import com.upgrad.Eshop.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Qualifier("eshopUserDAO")
    @Autowired
    private EshopUserDAO eshopUserDAO;

    @Override
    public EshopUser acceptUsersDetails(EshopUser eshopUser){
        eshopUser.setCreated(new Date());
        eshopUser.setUpdated(new Date());
        eshopUser.setRole("user");
        return eshopUserDAO.save(eshopUser);
    }

    @Override
    public EshopUser getUsersDetails(int id) throws UserNotFoundException {
        EshopUser users = eshopUserDAO.findById(id).orElseThrow(
                ()->  new UserNotFoundException("User Not found for " +id));
        return users;
    }

    @Override
    public String checkUserName(String userName) {
        String eshopUsers = userName;
        EshopUser username = eshopUserDAO.findByUsername(userName);

        if ( username != null)
            eshopUsers = null;
        return eshopUsers;
    }

    @Override
    public String checkEmail(String email){
        String eshopUsers = email;
        EshopUser emailId = eshopUserDAO.findByEmail(email);

        if(emailId != null)
            eshopUsers = null;

        return eshopUsers;
    }

    @Override
    public Boolean checkEmail2(String email){

        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,7}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    /**
     *Here I am checking that give string contain number or not and contain 10 numbers are not
     * I write a logic in such a way that if give string contain any alphabits it return false
     * else return true
     */
    @Override
    public Boolean checkPhoneNumber(String phoneNumber) {
        Matcher matcher;
        if (phoneNumber.length() == 10) {
            Pattern pattern = Pattern.compile( "[a-zA-Z]" );
            matcher = pattern.matcher(phoneNumber);
        }
        else return false;
        return !matcher.find();
    }

    @Override
    public Boolean checkPassword(String password) {
        if (password.length() >= 8){
            return true;
        }
        return false;
    }

    public UserDetails loadUserDetails(String userName) throws UserNotFoundException {
        //JWT: Changed Email
        EshopUser user = eshopUserDAO.findByUsername(userName);
        if (user == null) {
            throw new UserNotFoundException("No User Available" + userName);
        }
        return  new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword() , new ArrayList<>());
    }
}


