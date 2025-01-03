package com.CustomerManagement.controller;

import com.CustomerManagement.entity.Customer;
import com.CustomerManagement.model.CustomerModel;
import com.CustomerManagement.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.CustomerManagement.constants.Constants.CUSTOMER_URL;

@Slf4j
@RestController
@RequestMapping(CUSTOMER_URL)
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customer")
    public ResponseEntity<List<Customer>> getAllCustomers() {

        final String METHOD_NAME = this.getClass().getName() + " :: getAllCustomers ::";
        log.info(METHOD_NAME + "calling getAllCustomers" +
                " method ");
        List<Customer> customerList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @PostMapping(value = "/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerModel customerModel) {
        final String METHOD_NAME = this.getClass().getName() + " :: createCustomer ::";
        log.info(METHOD_NAME + "calling createCustomer" +
                " method with " + "customerModel :: {} ", customerModel);
        Customer savedCustomer = customerService.createCustomer(customerModel);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping(value = "/customer/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        final String METHOD_NAME = this.getClass().getName() + " :: getCustomerById ::";
        log.info(METHOD_NAME + "calling getCustomerById method with id :: {} ", id);
        return customerService.getCustomerById(id);
    }

    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<Map<String, Customer>> deleteCustomer(@PathVariable Long id) {
        final String METHOD_NAME = this.getClass().getName() + " :: deleteCustomer ::";
        log.info(METHOD_NAME + "calling deleteCustomer" + " method with " + "id :: {} ", id);
        Customer customer = customerService.deleteCustomer(id);
        Map<String, Customer> response = new HashMap<>();
        response.put("Data deleted successfully", customer);
        return ResponseEntity.ok(response);

    }
}
