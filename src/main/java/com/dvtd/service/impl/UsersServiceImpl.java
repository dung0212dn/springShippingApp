/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dvtd.pojo.Shippers;
import com.dvtd.pojo.Users;
import com.dvtd.repository.UsersRepository;
import com.dvtd.service.EmailSenderService;
import com.dvtd.service.UsersService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private EmailSenderService emailSender;

    @Override
    public List<Users> getUsers(Map<String, String> params) {
        return this.usersRepo.getUsers(params);
    }

    @Override
    public Users getUserDetail(int id) {
        return this.usersRepo.getUserDetail(id);
    }

    @Override
    public boolean addUser(Map<String, String> params, MultipartFile avatar) {
        return this.usersRepo.addUsers(params, "", avatar);
    }
    
    @Override
    public boolean addShipper(Map<String, String> params, MultipartFile avatar) {
        return this.usersRepo.addUsers(params,"SHIPPER_REGISTER", avatar);
    }

    @Override
    public boolean updateUser(Users u) {
        Users uTemp = this.usersRepo.getUserDetail(u.getUserID());
        boolean userUpdate = this.usersRepo.updateUsers(u); 
        if(u.getActive() != 0 && userUpdate == true && uTemp.getActive() == 0)
        {
             String subject = "THÔNG BÁO TRẠNG THÁI TÀI KHOẢN";
             String messageInform = "Tài khoản của bạn đã được kích hoạt thành công.";
             
             this.emailSender.sendEmail(u.getEmail(), subject, messageInform);  
        }
         
            
        return userUpdate;
    }

    @Override
    public int countUsers() {
        return this.usersRepo.countUsers();
    }

    @Override
    public boolean deleteUsers(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users u = this.usersRepo.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.usersRepo.authUser(username, password);
    }

    @Override
    public Users getUserByUsername(String username) {
        Users u = this.usersRepo.getUserByUsername(username);
        u.setPassword("");
        return u;
    }

    @Override
    public boolean existUsername(String username) {
        Users u = this.usersRepo.getUserByUsername(username);
        if (u != null) {
            return true;
        } else {
            return false;
        }
    }

    

}
