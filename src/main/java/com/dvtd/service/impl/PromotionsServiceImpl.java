/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.service.impl;

import com.dvtd.pojo.Promotions;
import com.dvtd.repository.PromotionsRepository;
import com.dvtd.service.PromotionsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class PromotionsServiceImpl implements PromotionsService{
    @Autowired
    private PromotionsRepository promotionRepo;
    
    @Override
    public List<Promotions> getPromotions(Map<String, String> params) {
        return this.promotionRepo.getPromotions(params);
    }

    @Override
    public Promotions getPromotionDetail(int id) {
        return this.promotionRepo.getPromotionDetail(id);
    }

    @Override
    public boolean addPromotion(Promotions p) {
        return this.promotionRepo.addPromotions(p);
    }

    @Override
    public boolean updatePromotion(Promotions p) {
        return this.promotionRepo.updatePromotion(p);
    }

    @Override
    public int countPromotions() {
       return this.promotionRepo.countPromotions();
    }

    @Override
    public boolean deletePromotion(int id) {
       return this.promotionRepo.deletePromotion(id);
    }

    @Override
    public Promotions getPromotionDetailByCode(String code) {
       return this.promotionRepo.getPromotionDetailByCode(code);
    }
    
    
    
}
