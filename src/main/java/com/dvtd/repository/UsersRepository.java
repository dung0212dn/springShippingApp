/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dvtd.repository;

import com.dvtd.pojo.Shippers;
import com.dvtd.pojo.Users;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
public interface UsersRepository {
    List<Users> getUsers(Map<String, String> params);
    Users getUserDetail(int id);
    boolean addUsers(Map<String, String> userInfo, String type, MultipartFile avatar);
    boolean updateUsers(Users u);
    int countUsers();
    boolean deleteUsers(int id);
    Users getUserByUsername(String username);
    boolean authUser(String username, String password);
}
