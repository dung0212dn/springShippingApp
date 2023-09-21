/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.formatters;

import com.dvtd.pojo.Users;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ACER
 */
public class UserFomatter implements Formatter<Users>{

    @Override
    public String print(Users users, Locale locale) {
        return String.valueOf(users.getUserID());
    }

    @Override
    public Users parse(String userID, Locale locale) throws ParseException {
        int userId = Integer.parseInt(userID);
        return new Users(userId);
    }
    
}
