package com.OrderManagementService.service;

import com.CustomerManagement.entity.Customer;
import com.OrderManagementService.client.CustomerFeignClient;
import com.OrderManagementService.entity.Order;
import com.OrderManagementService.entity.OrderList;
import com.OrderManagementService.exception.OrderException;
import com.OrderManagementService.model.OrderModel;
import com.OrderManagementService.repository.OrderListRepository;
import com.OrderManagementService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.OrderManagementService.constants.Constants.EXCEPTION_MESSAGE;
import static com.OrderManagementService.constants.Constants.INTERNAL_SERVER_GET_ERROR_MESSAGE;
import static com.OrderManagementService.constants.Constants.INTERNAL_SERVER_SAVE_ERROR_MESSAGE;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderListRepository orderListRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerFeignClient customerFeignClient;


    @Override
    public List<OrderList> getOrderDropdownList() {
        final String METHOD_NAME = this.getClass().getName() + " :: getOrderDropdownList :: ";
        log.info(METHOD_NAME);
        try {
            return orderListRepository.findInOrder();
        } catch (OrderException e) {
            log.info(METHOD_NAME + EXCEPTION_MESSAGE, e.getMessage(), e);
            throw new OrderException(INTERNAL_SERVER_GET_ERROR_MESSAGE);
        }
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        log.info("calling customerFeignClient to get the customer details");
        Customer customer = customerFeignClient.getCustomerById(customerId);
        if (Objects.isNull(customer)) {
            throw new OrderException("customer Id not found");
        } else
            return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Order> saveOrderForCustomer(List<OrderModel> orders) {
        final String METHOD_NAME = this.getClass().getName() + " :: saveOrderForCustomer :: ";
        log.info(METHOD_NAME);
        try {
            List<Order> orderList = orders.stream()
                    .map(orderModel -> Order.builder()
                            .item(orderModel.getItem())
                            .price(orderModel.getPrice())
                            .quantity(orderModel.getQuantity())
                            .orderTotal(orderModel.getPrice() * orderModel.getQuantity())
                            .customerId(orderModel.getCustomerId())
                            .build())
                    .toList();
            orderList.forEach(order -> orderRepository.save(order));
            log.info(METHOD_NAME + "Updating the stock value");
            this.updateStock(orderList);
            return orderList;
        } catch (OrderException e) {
            log.info(METHOD_NAME + EXCEPTION_MESSAGE, e.getMessage(), e);
            throw new OrderException(INTERNAL_SERVER_GET_ERROR_MESSAGE);
        }
    }

    public void updateStock(List<Order> orders) {
        final String METHOD_NAME = this.getClass().getName() + " :: updateStock :: ";
        log.info(METHOD_NAME + "orders :: {} ", orders);
        try {
            Map<String, Integer> orderItems = orders.stream()
                    .collect(Collectors.toMap(Order::getItem, Order::getQuantity));
            List<OrderList> orderList = orderListRepository.findAll();
            orderList.stream()
                    .filter(list -> orderItems.containsKey(list.getItem()) && list.getStock() > 0)
                    .forEach(list -> {
                        list.setStock(list.getStock() - orderItems.get(list.getItem()));
                        orderListRepository.save(list);
                    });
        } catch (OrderException e) {
            log.info(METHOD_NAME + EXCEPTION_MESSAGE, e.getMessage(), e);
            throw new OrderException(INTERNAL_SERVER_SAVE_ERROR_MESSAGE);
        }
    }

}
