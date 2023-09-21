/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dvtd.repository;

import com.dvtd.pojo.ShipperInfo;
import com.dvtd.pojo.Shippers;

/**
 *
 * @author ACER
 */
public interface ShippersRepository {
    Shippers getShipperByCMND(String CMND);
    ShipperInfo getShipperByUserId(int id);
}
