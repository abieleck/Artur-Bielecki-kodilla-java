package com.kodilla.good.patterns.challenges.order;

import com.kodilla.good.patterns.challenges.order.dispatch.DispatchService;
import com.kodilla.good.patterns.challenges.order.dispatch.DispatchServiceToScreen;
import com.kodilla.good.patterns.challenges.order.orders.Order;
import com.kodilla.good.patterns.challenges.order.orders.OrderProvider;
import com.kodilla.good.patterns.challenges.order.orders.OrderRepository;
import com.kodilla.good.patterns.challenges.order.orders.OrderRepositoryToScreen;
import com.kodilla.good.patterns.challenges.order.payment.PaymentRepository;
import com.kodilla.good.patterns.challenges.order.payment.PaymentRepositoryFromCsv;

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
