/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.controllers;

import com.dvtd.pojo.Users;
import com.dvtd.service.PromotionsService;
import com.dvtd.service.StatsService;
import com.dvtd.service.UsersService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ACER
 */
@Controller
@ControllerAdvice
public class HomeController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private StatsService statsService;

    @ModelAttribute
    public void commonAttribute(Model model, Principal principal) {
        if (principal != null) {
            Users currentUser = this.usersService.getUserByUsername(principal.getName());
            if ( currentUser != null) {
                model.addAttribute("currentUser", currentUser);
            }
        }
    }

    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
       
//        List<List<Map<String, Integer>>> canvasjsDataList = this.statsService.statsRevenue(params);
//        model.addAttribute("dataPointsList", canvasjsDataList);
        return "home.index";
    }
}
