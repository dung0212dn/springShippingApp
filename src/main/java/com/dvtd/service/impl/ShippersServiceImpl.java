/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.service.impl;

import com.dvtd.pojo.ShipperInfo;
import com.dvtd.pojo.Shippers;
import com.dvtd.repository.ShippersRepository;
import com.dvtd.service.ShippersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class ShippersServiceImpl implements ShippersService{

    @Autowired
    private ShippersRepository shipperRepo;
    
    @Override
    public boolean existCMND(String CMND) {
       if(this.shipperRepo.getShipperByCMND(CMND) != null)
           return true;
       return false;
    }

    @Override
    public ShipperInfo getShipperByUserId(int id) {
        ShipperInfo sInfo = this.shipperRepo.getShipperByUserId(id);
        sInfo.setPassword("");
        return sInfo;
    }
    
    
    
}
