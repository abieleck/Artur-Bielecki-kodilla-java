package com.kodilla.good.patterns.challenges.order;

import com.kodilla.good.patterns.challenges.order.Order;
import com.kodilla.good.patterns.challenges.order.OrderRepository;

public class OrderRepositoryToScreen implements OrderRepository {

    @Override
    public void store(Order order) {
        System.out.print("Order stored in database: ");
        System.out.println(order.toString());
    }
}
