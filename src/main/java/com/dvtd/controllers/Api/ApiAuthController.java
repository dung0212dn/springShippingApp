/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.controllers.Api;

import com.dvtd.components.JwtService;
import com.dvtd.pojo.Users;
import com.dvtd.service.ShippersService;
import com.dvtd.service.UsersService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/api")
public class ApiAuthController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ShippersService shippersService;

    @PostMapping("/login/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody Users user) {
        if (user.getUsername() == "" || user.getPassword() == "") {
            return new ResponseEntity<>("Tên đăng nhập và mật khẩu không được bỏ trống.", HttpStatus.BAD_REQUEST);
        }
        if (this.usersService.authUser(user.getUsername(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getUsername());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sai tên đăng nhập hoặc mật khẩu.", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(path = "/user-register/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<String> userRegister(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar) {
        if (this.usersService.existUsername(params.get("username")) == false) {
            if (this.usersService.addUser(params, avatar)) {
                return new ResponseEntity<>("Đăng kí thành công", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Tên đăng nhập đã tồn tại", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/shipper-register/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<String> shipperRegister(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar) {
        if (this.usersService.existUsername(params.get("username")) == true) {
            return new ResponseEntity<>("Tên đăng nhập đã tồn tại", HttpStatus.BAD_REQUEST);
        } else if (this.shippersService.existCMND(params.get("CMND")) == true) {
            return new ResponseEntity<>("CMND đã tồn tại", HttpStatus.BAD_REQUEST);
        } else {
            if (this.usersService.addShipper(params, avatar)) {
                return new ResponseEntity<>("Đăng kí thành công", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    
    
}
