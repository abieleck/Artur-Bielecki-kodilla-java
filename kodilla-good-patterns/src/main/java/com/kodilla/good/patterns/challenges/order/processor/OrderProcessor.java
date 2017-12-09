package com.kodilla.good.patterns.challenges.order.processor;

import com.kodilla.good.patterns.challenges.order.orders.Order;

public interface OrderProcessor {

    public OrderProcessDto process(Order order);

}
