/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dvtd.repository;

import com.dvtd.pojo.Promotions;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ACER
 */
public interface PromotionsRepository {
    List<Promotions> getPromotions(Map<String, String> params);
    Promotions getPromotionDetail(int id);
    boolean addPromotions(Promotions p);
    boolean updatePromotion(Promotions p);
    int countPromotions();
    boolean deletePromotion(int id);
    Promotions getPromotionDetailByCode(String code);
}
