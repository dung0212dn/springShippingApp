/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.service.impl;

import com.dvtd.pojo.Reviews;
import com.dvtd.pojo.Users;
import com.dvtd.repository.ReviewsRepository;
import com.dvtd.repository.UsersRepository;
import com.dvtd.service.ReviewsService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class ReviewsServiceImpl implements ReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepo;
    @Autowired
    private UsersRepository usersRepo;

    @Override
    public Reviews reviewCreate(Reviews review, int shipperID) {
        review.setCreatedDate(new Date());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users u = this.usersRepo.getUserByUsername(authentication.getName());
        review.setUserID(u);
        
        Users s = this.usersRepo.getUserDetail(shipperID);
        review.setShipperID(s);

        return this.reviewsRepo.reviewCreate(review);
    }

    @Override
    public List<Reviews> getReviewsByShipperID(int shipperID) {
        return this.reviewsRepo.getReviewsByShipperID(shipperID);
    }

}
