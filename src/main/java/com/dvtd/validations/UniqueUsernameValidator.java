///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.dvtd.service;
//
///**
// *
// * @author ACER
// */
//import com.dvtd.service.UsersService;
//import com.dvtd.validations.annotations.UniqueUsername;
//import org.springframework.stereotype.Component;
//
//import javax.validation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//
//@Component
//@Scope("request")
//public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
//
//    
//    private UsersService usersService;
//    
//    @Autowired
//    public UniqueUsernameValidator(UsersService uS){
//        this.usersService = uS;
//    }
//    
//    @Override
//    public void initialize(UniqueUsername constraintAnnotation) {
//       
//    }
//    
// 
//    @Override
//    public boolean isValid(String username, ConstraintValidatorContext context) {
//      if(this.usersService.existUsername(username) == false)
//          return true;
//      return false;
//         
//    }
//
//    
//
//   
//
//}
