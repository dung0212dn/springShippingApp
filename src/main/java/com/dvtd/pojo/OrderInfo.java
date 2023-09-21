/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.pojo;

import com.dvtd.pojo.Orderdetail;
import com.dvtd.pojo.Orders;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class OrderInfo {

    private Orders orderInfo;
    private List<Orderdetail> orderDetails;
    private String promotion;

   
    // Các phương thức getter và setter
    public Orders getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Orders orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<Orderdetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<Orderdetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    public OrderInfo() {
        
    }
    
     public OrderInfo(Orders orderInfo,List<Orderdetail> orderDetails) {
        this.orderInfo = orderInfo;
        this.orderDetails = orderDetails;
    }

    /**
     * @return the promotion
     */
    public String getPromotion() {
        return promotion;
    }

    /**
     * @param promotion the promotion to set
     */
    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

}
