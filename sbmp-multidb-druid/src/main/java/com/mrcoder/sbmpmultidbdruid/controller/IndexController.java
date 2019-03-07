package com.mrcoder.sbmpmultidbdruid.controller;

import com.mrcoder.sbmpmultidbdruid.entity.Order;
import com.mrcoder.sbmpmultidbdruid.entity.User;
import com.mrcoder.sbmpmultidbdruid.service.OrderService;
import com.mrcoder.sbmpmultidbdruid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.List;

/**
 * 前端控制器
 */
@RestController
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUserList() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping("/price")
    public ResponseEntity<BigDecimal> getPrice() {
        return ResponseEntity.ok(userService.getOrderPriceByUserId(1));
    }

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getOrderList() {
        return ResponseEntity.ok(orderService.getOrderList());
    }

    @GetMapping("/price2")
    public ResponseEntity<BigDecimal> getPrice2() {
        return ResponseEntity.ok(orderService.getOrderPriceByUserId(1));
    }

}