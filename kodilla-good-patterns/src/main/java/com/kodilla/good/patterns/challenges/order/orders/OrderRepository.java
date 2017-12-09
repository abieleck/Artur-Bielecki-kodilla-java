package com.kodilla.good.patterns.challenges.order.orders;

import com.kodilla.good.patterns.challenges.order.orders.Order;

public interface OrderRepository {
    void store(Order order);
}
