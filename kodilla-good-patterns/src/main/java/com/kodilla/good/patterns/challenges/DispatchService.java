package com.kodilla.good.patterns.challenges;

public class DispatchService {

    public boolean sendOrder(Order order) {

        // to illustrate behaviour if order is dispatched and not dispatched, only orders with odd IDs are dispatched
        if (order.getOrderId() % 2 == 1) {
            System.out.println("Order No " + order.getOrderId() + " sent to " + order.getUser().getAddress());
            return true;
        } else {
            return false;
        }

    }
}
