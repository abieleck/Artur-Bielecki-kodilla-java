package com.kodilla.good.patterns.challenges;

import com.kodilla.good.patterns.challenges.dispatch.DispatchService;
import com.kodilla.good.patterns.challenges.dispatch.DispatchServiceToScreen;
import com.kodilla.good.patterns.challenges.order.Order;
import com.kodilla.good.patterns.challenges.order.OrderProvider;
import com.kodilla.good.patterns.challenges.order.OrderRepository;
import com.kodilla.good.patterns.challenges.order.OrderRepositoryToScreen;
import com.kodilla.good.patterns.challenges.payment.PaymentRepository;
import com.kodilla.good.patterns.challenges.payment.PaymentRepositoryFromCsv;

import java.util.stream.Stream;

public class OrderServiceRunner {

    public static void main(String[] args) {

        OrderProvider orderProvider = new OrderProvider();
        OrderRepository orderRepository = new OrderRepositoryToScreen();
        PaymentRepository paymentRepository = new PaymentRepositoryFromCsv();
        DispatchService dispatchService = new DispatchServiceToScreen();
        ProductOrderService productOrderService = new ProductOrderService(orderRepository, paymentRepository,
                dispatchService);

        Stream<Order> orders = orderProvider.getOrders();
        orders.forEach(productOrderService::serviceOrder);

    }

}
