/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dvtd.repository;

import com.dvtd.pojo.Bid;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ACER
 */
public interface BidsRepository {

    Bid bidCreate(int orderId, Map<String, String> bidInfo);

    List<Bid> getAllBidByOrderId(int orderId, Map<String, String> params);

    Bid getBidOrderByShipperId(int orderId, int userId);

    Bid updateBid(Bid bid);

   List<Bid> getAllBidByShipperId(Map<String, String> params, int userID);
}
