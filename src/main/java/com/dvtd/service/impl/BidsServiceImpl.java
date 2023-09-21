/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.service.impl;

import com.dvtd.pojo.Bid;
import com.dvtd.repository.BidsRepository;
import com.dvtd.service.BidsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class BidsServiceImpl implements BidsService{
    
    @Autowired
    private BidsRepository bidRepo;

    @Override
    public Bid bidCreate(int orderId, Map<String, String> bidInfo) {
        return this.bidRepo.bidCreate(orderId, bidInfo);
    }

    @Override
    public List<Bid> getAllBidByOrderId(int orderId, Map<String, String> params) {
      return this.bidRepo.getAllBidByOrderId(orderId, params);
    }

    @Override
    public Bid getBidOrderByShipperId(int orderId, int userId) {
        return this.bidRepo.getBidOrderByShipperId(orderId, userId);
    }

//    @Override
//    public List<Bid> getAllBidByShipperId(Map<String, String> params) {
//        return this.bidRepo.getAllBidByShipperId(params);
//    }
    
}
