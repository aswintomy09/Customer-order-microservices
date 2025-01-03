package com.OrderManagementService.repository;

import com.OrderManagementService.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.OrderManagementService.constants.Constants.GET_ITEMS_WITH_ORDER_QUERY;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Integer> {

    @Query(GET_ITEMS_WITH_ORDER_QUERY)
    List<OrderList> findInOrder();
}
