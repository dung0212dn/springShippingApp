/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.service.impl;

import com.dvtd.pojo.Bid;
import com.dvtd.pojo.OrderInfo;
import com.dvtd.pojo.Orderdetail;
import com.dvtd.pojo.Orders;
import com.dvtd.pojo.Promotions;
import com.dvtd.pojo.Users;
import com.dvtd.repository.BidsRepository;
import com.dvtd.repository.OrdersRepository;
import com.dvtd.repository.UsersRepository;
import com.dvtd.service.EmailSenderService;
import com.dvtd.service.OrdersService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepo;
    @Autowired
    private BidsRepository bidsRepo;
    @Autowired
    private UsersRepository usersRepo;
    @Autowired
    private EmailSenderService emailSender;

    @Override
    public Orders createOrder(Orders orderInfo, List<Orderdetail> orderDetail, String promotion) {
        return this.ordersRepo.createOrder(orderInfo, orderDetail, promotion);
    }

    @Override
    public OrderInfo getOrderById(int id) {
        return this.ordersRepo.getOrderInfoById(id);
    }

    @Override
    public List<Orderdetail> getOrderDetailById(int id) {
        return this.ordersRepo.getOrderDetailsByOrderId(id);
    }

    @Override
    public boolean chooseShipperForOrder(int orderID, int shipperID) {
        Orders o = this.ordersRepo.getOrderById(orderID);

        Users s = this.usersRepo.getUserDetail(shipperID);
        o.setChosenShipperID(s);
        o.setStatus("IS_SHIPPING");
        
        Bid bGet = this.bidsRepo.getBidOrderByShipperId(orderID, shipperID);
        bGet.setIsChosen(true);
        
        Bid bidUpdate = this.bidsRepo.updateBid(bGet);
        
        int newOrderTotal = o.getOrderTotal() + bGet.getPrice();
        o.setOrderTotal(newOrderTotal);

        Orders orderUpdate = this.ordersRepo.update(o);

        if (orderUpdate != null && bidUpdate != null) {
            List<Bid> bids = this.bidsRepo.getAllBidByOrderId(orderID, null);
            ArrayList<String> emailReject = new ArrayList<>();
            for (Bid b : bids) {
                Users u = this.usersRepo.getUserDetail(b.getShipperID().getUserID());
                if (u.getUserID() != shipperID) {
                    emailReject.add(u.getEmail());
                }
            }

            String subject = "THÔNG BÁO KẾT QUẢ ĐẤU GIÁ";
            String messageReject = "Rất tiếc bạn không được chọn cho đơn số " + String.valueOf(orderID) + "!";
            String messageConfirmed = "Chúc mừng bạn đã được chọn để giao đơn hàng số " + String.valueOf(orderID) + ".";

            this.emailSender.sendEmailMultiRecepient(emailReject, subject, messageReject);
            this.emailSender.sendEmail(this.usersRepo.getUserDetail(shipperID).getEmail(), subject, messageConfirmed);
            return true;
        }
        return false;
    }

    @Override
    public OrderInfo updateStatus(String status, int orderID) {
        Orders o = this.ordersRepo.getOrderById(orderID);
        o.setStatus(status);

        Orders orderUpdate = this.ordersRepo.update(o);
        
        

        String subject = "THÔNG BÁO TRẠNG THÁI ĐƠN HÀNG";
        String messageInform = "";
        
        if (orderUpdate != null) {
            OrderInfo of = this.ordersRepo.getOrderInfoById(orderUpdate.getOrderID());
            switch (status) {
                case "IS_DELIVERED": {
                    messageInform = "Đơn hàng số " + String.valueOf(orderID) + " của bạn đã được giao thành công.";
                    break;
                }
                case "IS_CANCEL":{
                   messageInform = "Rất tiếc. Đơn hàng số " + String.valueOf(orderID) + " của bạn bị hủy.";
                   break;
                }
            }

            this.emailSender.sendEmail(o.getUserID().getEmail(), subject, messageInform);
            return of;
        }
        return null;
    }

    @Override
    public List<Orders> getAllOrders(Map<String, String> params) {
      return this.ordersRepo.getAllOrders(params);
    }

    @Override
    public int countOrders() {
      return this.ordersRepo.countOrders();
    }

    @Override
    public List<Orders> getAllOrdersByUserID(Map<String, String> params) {
        return this.ordersRepo.getAllOrdersByUserID(params);
    }

    @Override
    public List<Orders> getAllOrdersByBids(Map<String, String> params) {
        return this.ordersRepo.getAllOrdersByBids(params);
    }

}
