/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dvtd.repository;

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
public interface OrdersRepository {

    OrderInfo getOrderInfoById(int id);

    Orders createOrder(Orders orderInfo, List<Orderdetail> orderDetail, String promotion);

    Orders update(Orders orderInfo);

    Orders getOrderById(int id);

    List<Orderdetail> getOrderDetailsByOrderId(int orderId);
//    boolean chooseShipperForOrder(int orderID, int shipperID);

    List<Orders> getAllOrders(Map<String, String> params);
    

    List<Orders> getAllOrdersByUserID(Map<String, String> params);

    int countOrders();

    List<Orders> getAllOrdersByBids(Map<String, String> params);

}
