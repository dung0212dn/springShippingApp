/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.controllers.Api;

import com.dvtd.pojo.Bid;
import com.dvtd.pojo.OrderInfo;
import com.dvtd.service.BidsService;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/api")
public class ApiBidsController {

    @Autowired
    private BidsService bidService;
    
    @PostMapping(path = "/orders/{id}/bids/create/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Bid> bidCreate(@RequestBody Map<String, String> bidInfo, @PathVariable("id") int id) {
        Bid bidCreate = this.bidService.bidCreate(id, bidInfo);
        if (bidCreate != null) {
            return new ResponseEntity<>(bidCreate, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(path = "/orders/{id}/bids/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Bid>> getAllBidByOrderId(@PathVariable("id") int id,@RequestParam Map<String, String> params) {
        if (this.bidService.getAllBidByOrderId(id, params) != null) {
            return new ResponseEntity<>(this.bidService.getAllBidByOrderId(id, params), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(path = "/orders/{id}/bids/{userID}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Bid> getBidOrderByShipperId(@PathVariable("id") int orderID, @PathVariable("userID") int userID) {
         
            return new ResponseEntity<>(this.bidService.getBidOrderByShipperId(orderID, userID), HttpStatus.OK);
     
       
    }
    
//    @PostMapping("/orders/{id}/update/")
//    @CrossOrigin
//    public ResponseEntity<String> choose

}
