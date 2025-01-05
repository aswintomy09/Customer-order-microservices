package com.OrderManagementService.client;

import com.CustomerManagement.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.OrderManagementService.constants.Constants.LOCALHOST_8080;

@FeignClient(value = "customer", url = LOCALHOST_8080)
public interface CustomerFeignClient {

    @GetMapping(value = "/api/v1/customer/{id}")
    Customer getCustomerById(@PathVariable Long id);
}
