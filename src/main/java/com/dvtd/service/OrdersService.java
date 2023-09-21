/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dvtd.service;

import com.dvtd.pojo.OrderInfo;
import com.dvtd.pojo.Orderdetail;
import com.dvtd.pojo.Orders;
import com.dvtd.pojo.Promotions;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ACER
 */
public interface OrdersService {

    Orders createOrder(Orders orderInfo, List<Orderdetail> orderDetail, String promotion);

    OrderInfo getOrderById(int id);

    List<Orderdetail> getOrderDetailById(int id);

    boolean chooseShipperForOrder(int orderID, int shipperID);

    OrderInfo updateStatus(String status, int orderID);

    List<Orders> getAllOrders(Map<String, String> params);

    int countOrders();

    List<Orders> getAllOrdersByUserID(Map<String, String> params);
    
    List<Orders> getAllOrdersByBids(Map<String, String> params);
}
