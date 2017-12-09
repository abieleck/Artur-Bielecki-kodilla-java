package com.kodilla.good.patterns.challenges.order.orders;

import com.kodilla.good.patterns.challenges.order.dispatch.DispatchService;
import com.kodilla.good.patterns.challenges.order.payment.PaymentRepository;
import com.kodilla.good.patterns.challenges.order.processor.NewOrderProcessor;
import com.kodilla.good.patterns.challenges.order.processor.OrderProcessor;
import com.kodilla.good.patterns.challenges.order.processor.PaidOrderProcessor;
import com.kodilla.good.patterns.challenges.order.processor.SentOrderProcessor;

import java.util.function.BiFunction;

public enum OrderStatus {
    NEW((paymentRepository, dispatchService) -> new NewOrderProcessor(paymentRepository)),
    PAID((paymentRepository, dispatchService) -> new PaidOrderProcessor(dispatchService)),
    SENT((paymentRepository, dispatchService) -> new SentOrderProcessor());

    BiFunction<PaymentRepository, DispatchService, OrderProcessor> processorSupplier;

    OrderStatus(BiFunction<PaymentRepository, DispatchService, OrderProcessor> processorSupplier) {
        this.processorSupplier = processorSupplier;
    }

    public OrderProcessor getProcessorForStatus(PaymentRepository paymentRepository, DispatchService dispatchService) {

        return processorSupplier.apply(paymentRepository, dispatchService);

    }
}
