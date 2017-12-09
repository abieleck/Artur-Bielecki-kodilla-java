package com.kodilla.good.patterns.challenges.order.processor;

import com.kodilla.good.patterns.challenges.order.orders.Order;
import com.kodilla.good.patterns.challenges.order.orders.OrderStatus;
import com.kodilla.good.patterns.challenges.order.payment.Payment;
import com.kodilla.good.patterns.challenges.order.payment.PaymentRepository;

public class NewOrderProcessor implements OrderProcessor {

    private PaymentRepository paymentRepository;

    public NewOrderProcessor(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public OrderProcessDto process(Order order) {

        OrderStatus newStatus;
        boolean readyForFurtherProcessing;

        Payment payment = paymentRepository.getPaymentForOrder(order);
        if (payment == null) {
            newStatus = order.getOrderStatus();
            readyForFurtherProcessing = false;
        } else {
            newStatus = OrderStatus.PAID;
            readyForFurtherProcessing = true;
        }

        Order processedOrder = new Order(order.getOrderId(), order.getUser(), order.getOrderedItems(), newStatus);
        return new OrderProcessDto(processedOrder, readyForFurtherProcessing, ActionType.PAYMENT);

    }
}
