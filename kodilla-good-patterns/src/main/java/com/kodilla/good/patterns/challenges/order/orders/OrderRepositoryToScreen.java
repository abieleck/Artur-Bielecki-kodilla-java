package com.kodilla.good.patterns.challenges.order.orders;

import com.kodilla.good.patterns.challenges.order.orders.Order;
import com.kodilla.good.patterns.challenges.order.orders.OrderRepository;

public class OrderRepositoryToScreen implements OrderRepository {

    @Override
    public void store(Order order) {
        System.out.print("Order stored in database: ");
        System.out.println(order.toString());
    }
}
