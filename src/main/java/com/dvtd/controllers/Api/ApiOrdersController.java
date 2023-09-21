/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.controllers.Api;

import com.dvtd.pojo.Orders;
import com.dvtd.pojo.OrderInfo;
import com.dvtd.repository.OrdersRepository;
import com.dvtd.service.EmailSenderService;
import com.dvtd.service.OrdersService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/api")
public class ApiOrdersController {

    @Autowired
    private OrdersService orderService;
    @Autowired
    private OrdersRepository orderRepo;

    @GetMapping(path = "/orders/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Orders>> getAllOrders(@RequestParam Map<String, String> params) {
        List<Orders> listOrder = this.orderService.getAllOrders(params);

        if (listOrder == null || listOrder.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listOrder, HttpStatus.OK);

    }

    @PostMapping(path = "/orders/create/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<OrderInfo> createOrder(@RequestBody OrderInfo request) {
        Orders o = new Orders();
        if (request.getPromotion() != null) {
            o = this.orderRepo.createOrder(request.getOrderInfo(), request.getOrderDetails(), request.getPromotion());
        } else {
            o = this.orderRepo.createOrder(request.getOrderInfo(), request.getOrderDetails(), "");
        }

        OrderInfo of = this.orderService.getOrderById(o.getOrderID());
        if (o != null) {
            return new ResponseEntity<>(of, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/orders/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<OrderInfo> getOrderInfo(@PathVariable("id") int id) {

        OrderInfo o = this.orderService.getOrderById(id);
        if (o != null) {
            return new ResponseEntity<>(o, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
    }

    @PostMapping(path = "/orders/{id}/choose-shipper/{shipperID}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<String> chooseShipperForOrder(@PathVariable("id") int orderID, @PathVariable("shipperID") int shipperID) {

        if (this.orderService.chooseShipperForOrder(orderID, shipperID) == true) {
            return new ResponseEntity<>("Chọn shipper thành công", HttpStatus.OK);
        }
        return new ResponseEntity<>("Có lỗi xảy ra", HttpStatus.BAD_GATEWAY);
    }

    @PostMapping(path = "/orders/{orderID}/update-status/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<OrderInfo> updateOrderStatus(@PathVariable("orderID") int orderID, @RequestBody Orders order) {
        OrderInfo o = this.orderService.updateStatus(order.getStatus(), orderID);
        if (o != null) {
            return new ResponseEntity<>(o, HttpStatus.OK);
        }
        return new ResponseEntity<>(o, HttpStatus.BAD_GATEWAY);
    }

    @GetMapping(path = "/orders/user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Orders>> getAllOrdersByUserID(@RequestParam Map<String, String> params) {
        List<Orders> o = this.orderService.getAllOrdersByUserID(params);
        if (!o.isEmpty()) {
            return new ResponseEntity<>(o, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/orders/shippers/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Orders>> getAllOrdersByShipperID(@RequestParam Map<String, String> params) {
        List<Orders> o = this.orderService.getAllOrdersByBids(null);
        if (o == null || o.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        BindingResult bindingResult = ex.getBindingResult();
//        Map<String, String> errors = new HashMap<>();
//
//        for (FieldError fieldError : bindingResult.getFieldErrors()) {
//            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
//        }
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
//    }
}
