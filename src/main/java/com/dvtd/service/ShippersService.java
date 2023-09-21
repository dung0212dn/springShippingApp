/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dvtd.service;

import com.dvtd.pojo.ShipperInfo;
import com.dvtd.pojo.Shippers;

/**
 *
 * @author ACER
 */
public interface ShippersService {
  boolean existCMND(String CMND);
  ShipperInfo getShipperByUserId(int id);
}
