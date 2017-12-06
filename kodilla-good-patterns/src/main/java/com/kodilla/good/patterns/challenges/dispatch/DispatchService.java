package com.kodilla.good.patterns.challenges.dispatch;

import com.kodilla.good.patterns.challenges.order.Order;

public interface DispatchService {
    boolean sendOrder(Order order);
}
