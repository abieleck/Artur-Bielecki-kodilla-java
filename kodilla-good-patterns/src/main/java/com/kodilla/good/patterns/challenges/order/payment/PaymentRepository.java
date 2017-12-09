package com.kodilla.good.patterns.challenges.order.payment;

import com.kodilla.good.patterns.challenges.order.orders.Order;

public interface PaymentRepository {
    Payment getPaymentForOrder(Order order);
}
