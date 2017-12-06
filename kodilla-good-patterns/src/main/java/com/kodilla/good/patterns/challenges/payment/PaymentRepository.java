package com.kodilla.good.patterns.challenges.payment;

import com.kodilla.good.patterns.challenges.order.Order;

public interface PaymentRepository {
    Payment getPaymentForOrder(Order order);
}
