package com.kodilla.good.patterns.challenges.processor;

import com.kodilla.good.patterns.challenges.order.Order;

public interface OrderProcessor {

    public OrderProcessDto process(Order order);

}
