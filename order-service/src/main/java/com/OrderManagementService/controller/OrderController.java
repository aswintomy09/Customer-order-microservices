package com.OrderManagementService.controller;

import com.OrderManagementService.entity.Order;
import com.OrderManagementService.entity.OrderList;
import com.OrderManagementService.model.OrderModel;
import com.OrderManagementService.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.OrderManagementService.constants.Constants.CUSTOMER_URL;

@Slf4j
@RestController
@RequestMapping(CUSTOMER_URL)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/dropdown")
    public ResponseEntity<List<OrderList>> getOrderDropdownList() {
        final String METHOD_NAME = this.getClass().getName() + " :: getOrderDropdownList ::";
        log.info(METHOD_NAME + "calling getOrderDropdownList method ");
        List<OrderList> orderList = orderService.getOrderDropdownList();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping("/order/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable("customerId") Long customerId) {
        final String METHOD_NAME = this.getClass().getName() + " :: getOrdersByCustomerId ::";
        log.info(METHOD_NAME + "calling getOrdersByCustomerId" +
                " method with " + "customerId :: {} ", customerId);
        List<Order> savedOrderList = orderService.getOrdersByCustomerId(customerId);
        return new ResponseEntity<>(savedOrderList, HttpStatus.OK);
    }

    @PostMapping(value = "/order")
    public ResponseEntity<List<Order>> saveOrderForCustomer(@RequestBody List<OrderModel> orderModels) {
        final String METHOD_NAME = this.getClass().getName() + " :: saveOrderForCustomer ::";
        log.info(METHOD_NAME + "calling saveOrderForCustomer" +
                " method with " + "orderModels :: {} ", orderModels);
        List<Order> savedOrderList = orderService.saveOrderForCustomer(orderModels);
        return new ResponseEntity<>(savedOrderList, HttpStatus.CREATED);
    }
}
