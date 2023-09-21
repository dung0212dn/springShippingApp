/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.controllers.Api;

import com.dvtd.pojo.ShipperInfo;
import com.dvtd.pojo.Shippers;
import com.dvtd.pojo.Users;
import com.dvtd.service.ShippersService;
import com.dvtd.service.UsersService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/api")
public class ApiUsersController {
    @Autowired
    private ShippersService shippersService;
     @Autowired
    private UsersService usersService;
    
    @GetMapping("/shippers/{id}/")
    @CrossOrigin
    public ResponseEntity<ShipperInfo> getShipperInfo(@PathVariable("id") int id) {
        ShipperInfo s = this.shippersService.getShipperByUserId(id);
        if (s != null) {
            return new ResponseEntity<>(s, HttpStatus.OK);
        }
        return null;
        
    }
    
    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Users> details(Principal user) {
        Users u = this.usersService.getUserByUsername(user.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
