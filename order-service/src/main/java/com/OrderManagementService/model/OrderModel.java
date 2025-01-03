package com.OrderManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderModel {

    private Long id;

    private Integer customerId;

    private String item;

    private Integer price;

    private Integer quantity;

    private Integer orderTotal;
}
