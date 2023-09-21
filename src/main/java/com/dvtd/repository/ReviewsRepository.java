/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dvtd.repository;

import com.dvtd.pojo.Reviews;
import com.dvtd.pojo.Users;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface ReviewsRepository {
    Reviews reviewCreate(Reviews review);
    List<Reviews> getReviewsByShipperID(int shipperID);
}
