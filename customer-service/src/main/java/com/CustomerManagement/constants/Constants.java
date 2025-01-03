package com.CustomerManagement.constants;

public class Constants {

    // url constants
    public static final String CUSTOMER_URL = "/api/v1";
    public static final String ORDER_URL = "/api/v1/order";

    // message constants
    public static final String CUSTOMER_DOES_NOT_EXIST = "Customer does not exist with id";
    public static final String EXCEPTION_MESSAGE = "exception :: {}";
    public static final String INTERNAL_SERVER_SAVE_ERROR_MESSAGE = "Saving failed: Internal Server Error";
    public static final String INTERNAL_SERVER_GET_ERROR_MESSAGE = "Error loading data: Internal Server Error";

    // query constants
    public static final String GET_CUSTOMERS_WITH_ORDER_QUERY = "select distinct c from Customer c join fetch c.orders";
    public static final String GET_ITEMS_WITH_ORDER_QUERY = "select o from OrderList o order by o.item asc";
}
