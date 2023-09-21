/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.controllers;

import com.dvtd.commons.Breadcrumbs;
import com.dvtd.pojo.Promotions;
import com.dvtd.repository.PromotionsRepository;
import com.dvtd.service.PromotionsService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ACER
 */
@Controller
@PropertySource("classpath:configs.properties")
public class PromotionsController {

    @Autowired
    private PromotionsRepository promotionsRepository;
    @Autowired
    private PromotionsService promotionService;
    @Autowired
    private Environment env;
    

    @GetMapping("/promotions")
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("promotions", this.promotionService.getPromotions(params));
        String sortOption = params.get("sort");
        model.addAttribute("sortOption", sortOption);
       
        
        List<Breadcrumbs> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new Breadcrumbs("Khuyến mãi", ""));
        model.addAttribute("breadcrumbs", breadcrumbs);
        
        
        
        String currentPage = params.get("page");
        if( currentPage != null && !currentPage.isEmpty())
             model.addAttribute("currentPage", currentPage);
        else model.addAttribute("currentPage", 1);
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.promotionService.countPromotions();
        model.addAttribute("countPromotions", Math.ceil(count * 1.0 / pageSize));
        return "promotions.index";
    }

    @GetMapping("/promotions/{id}")
    public String show(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("promotion", this.promotionService.getPromotionDetail(id));
        return "promotions.show";
    }
    
    @GetMapping("/promotions/create")
    public String create(Model model) {
        model.addAttribute("promotion", new Promotions());
        
        List<Breadcrumbs> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new Breadcrumbs("Khuyến mãi", "/promotions"));
        breadcrumbs.add(new Breadcrumbs("Thêm khuyến mãi", ""));
        model.addAttribute("breadcrumbs", breadcrumbs);
        return "promotions.create";

    }

    @PostMapping("/promotions")
    public String store(@ModelAttribute("promotion") @Valid Promotions p, BindingResult rs, Model model, RedirectAttributes ra) {
        if (!rs.hasErrors()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            String formattedDate = sdf.format(now); //
            p.setCreatedDate(now);
            model.addAttribute("promotionsss", p);
            if (this.promotionService.addPromotion(p) == true) {
                ra.addFlashAttribute("messageSuccess", "Thêm khuyến mãi thành công");
                return "redirect:/promotions";
            }
            else {
                model.addAttribute("messageFalse", "Có lỗi xảy ra.");
            }
        }
        return "promotions.create";
    }
    
    @PostMapping("/promotions/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("promotion") @Valid Promotions p, BindingResult rs, Model model, RedirectAttributes ra)
    {
        if (!rs.hasErrors()) {
            if (this.promotionService.updatePromotion(p) == true) {
                ra.addFlashAttribute("messageSuccess", "Cập nhật khuyến mãi thành công");
                return "redirect:/promotions";
            }
            else {
                model.addAttribute("messageFalse", "Có lỗi xảy ra.");
            }
        }
       
        return "promotions.show";
    }
    
    @PostMapping("/promotions/delete")
    public String delete(@RequestParam("promotionId") int id, Model model, RedirectAttributes ra)
    {   
            
            if (this.promotionService.deletePromotion(id) == true) {
                
                ra.addFlashAttribute("messageSuccess", "Xóa khuyến mãi thành công");
            }
            else {
               
                ra.addFlashAttribute("messageSuccess", "Cập nhật khuyến mãi thành công");
            }
     return "redirect:/promotions";
        
    }
    
    
}
