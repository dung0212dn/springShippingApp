/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dvtd.service;

import com.dvtd.pojo.Users;
import com.dvtd.pojo.Users;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
public interface UsersService extends UserDetailsService{
    List<Users> getUsers(Map<String, String> params);
    Users getUserDetail(int id);
    boolean addUser(Map<String, String> params, MultipartFile avatar);
    boolean updateUser(Users p);
    int countUsers();
    boolean deleteUsers(int id);
    boolean authUser(String username, String password);
    Users getUserByUsername(String username);
    boolean existUsername(String username);
    boolean addShipper(Map<String, String> params, MultipartFile avatar);
}
