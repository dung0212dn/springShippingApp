/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.controllers;

import com.dvtd.commons.Breadcrumbs;
import com.dvtd.pojo.Users;
import com.dvtd.service.BidsService;
import com.dvtd.service.OrdersService;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ACER
 */
@Controller
public class OrdersController {
    
    @Autowired
    private BidsService bidsService;
    @Autowired
    private OrdersService orderService;
    @Autowired
    private Environment env;
    
    @GetMapping("/orders")
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("orders", this.orderService.getAllOrders(params));
        String sortOption = params.get("sort");
        model.addAttribute("sortOption", sortOption);
        
        List<Breadcrumbs> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new Breadcrumbs("Đơn hàng", ""));
        model.addAttribute("breadcrumbs", breadcrumbs);
        
        String currentPage = params.get("page");
        if( currentPage != null && !currentPage.isEmpty())
             model.addAttribute("currentPage", currentPage);
        else model.addAttribute("currentPage", 1);
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.orderService.countOrders();
        model.addAttribute("countOrders", Math.ceil(count * 1.0 / pageSize));
        return "orders.index";
    }
    
    @GetMapping("/orders/{orderID}/")
    public String show(Model model, @PathVariable("orderID") int orderID) {
      model.addAttribute("orders", this.orderService.getOrderById(orderID));
      Users u = this.orderService.getOrderById(orderID).getOrderInfo().getChosenShipperID();
      
      DecimalFormat decimalFormat = new DecimalFormat("#,###");
      if(u != null)
          model.addAttribute("total", decimalFormat.format(this.bidsService.getBidOrderByShipperId(orderID, u.getUserID()).getPrice()));
      else model.addAttribute("total", "");
      return "orders.show";
    }
}
