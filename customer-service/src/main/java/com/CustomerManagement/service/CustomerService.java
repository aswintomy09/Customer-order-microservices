package com.CustomerManagement.service;

import com.CustomerManagement.entity.Customer;
import com.CustomerManagement.model.CustomerModel;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer deleteCustomer(Long id);

    Customer createCustomer(CustomerModel customer);

}
