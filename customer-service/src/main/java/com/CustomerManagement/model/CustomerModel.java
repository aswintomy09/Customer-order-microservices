package com.CustomerManagement.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerModel {

    private String firstname;

    private String lastname;

    private String address;

    private String city;

}
