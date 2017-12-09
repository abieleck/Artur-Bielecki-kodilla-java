package com.kodilla.good.patterns.challenges.order.dispatch;

import com.kodilla.good.patterns.challenges.order.orders.Order;

public interface DispatchService {
    boolean sendOrder(Order order);
}
