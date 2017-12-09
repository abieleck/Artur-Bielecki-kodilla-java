package com.kodilla.good.patterns.challenges.suppliers.order;

import java.util.stream.Stream;

public interface OrderProvider {

    Stream<Order> getOrders();

}
