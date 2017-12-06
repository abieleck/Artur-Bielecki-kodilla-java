package com.kodilla.good.patterns.challenges.processor;

import com.kodilla.good.patterns.challenges.Order;
import com.kodilla.good.patterns.challenges.OrderProcessDto;

public interface OrderProcessor {

    public OrderProcessDto process(Order order);

}
