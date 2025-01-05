package com.OrderManagementService.service;

import com.OrderManagementService.entity.Order;
import com.OrderManagementService.entity.OrderList;
import com.OrderManagementService.model.OrderModel;

import java.util.List;

public interface OrderService {
    List<OrderList> getOrderDropdownList();

    List<Order> getOrdersByCustomerId(Long customerId);

    List<Order> saveOrderForCustomer(List<OrderModel> orders);
}
