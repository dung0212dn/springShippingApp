/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.controllers.Api;

import com.dvtd.pojo.OrderInfo;
import com.dvtd.pojo.Promotions;
import com.dvtd.service.PromotionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class ApiPromotionsController {
    
    @Autowired
    private PromotionsService promotionsService;
    
    @PostMapping(path = "/promotions/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Promotions> getPromotionsByCode(@RequestBody Promotions promotions) {
        Promotions p = this.promotionsService.getPromotionDetailByCode(promotions.getCode());
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
