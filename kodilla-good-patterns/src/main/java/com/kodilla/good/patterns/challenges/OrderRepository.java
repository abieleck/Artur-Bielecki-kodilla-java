package com.kodilla.good.patterns.challenges;

public class OrderRepository {

    public void store(Order order) {
        System.out.print("Order stored in database: ");
        System.out.println(order.toString());
    }
}
