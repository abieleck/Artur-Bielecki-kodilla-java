package com.kodilla.good.patterns.challenges;

import java.util.stream.Stream;

public class OrderServiceRunner {

    public static void main(String[] args) {

        OrderProvider orderProvider = new OrderProvider();
        OrderRepository orderRepository = new OrderRepository();
        PaymentRepository paymentRepository = new PaymentRepository();
        DispatchService dispatchService = new DispatchService();
        ProductOrderService productOrderService = new ProductOrderService(orderRepository, paymentRepository,
                dispatchService);

        Stream<Order> orders = orderProvider.getOrders();
        orders.forEach(productOrderService::serviceOrder);

    }

}
