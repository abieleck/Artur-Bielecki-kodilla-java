package com.kodilla.good.patterns.challenges.payment;

import java.time.LocalDate;

public final class Payment {

    private final long orderId;
    private final LocalDate paymentDate;

    public Payment(long orderId, LocalDate paymentDate) {
        this.orderId = orderId;
        this.paymentDate = paymentDate;
    }

    public long getOrderId() {
        return orderId;
    }
}
