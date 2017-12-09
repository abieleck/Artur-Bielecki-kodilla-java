package com.kodilla.good.patterns.challenges.order;

import com.kodilla.good.patterns.challenges.order.dispatch.DispatchService;
import com.kodilla.good.patterns.challenges.order.infoservice.InformationService;
import com.kodilla.good.patterns.challenges.order.orders.Order;
import com.kodilla.good.patterns.challenges.order.orders.OrderRepository;
import com.kodilla.good.patterns.challenges.order.orders.OrderStatus;
import com.kodilla.good.patterns.challenges.order.payment.PaymentRepository;
import com.kodilla.good.patterns.challenges.order.processor.ActionType;
import com.kodilla.good.patterns.challenges.order.processor.OrderProcessDto;
import com.kodilla.good.patterns.challenges.order.processor.OrderProcessor;

public class ProductOrderService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;
    private DispatchService dispatchService;

    public ProductOrderService(OrderRepository orderRepository, PaymentRepository paymentRepository,
                               DispatchService dispatchService) {

        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.dispatchService = dispatchService;

    }

    public void serviceOrder(Order order) {

        OrderProcessDto orderProcessDto;

        do {
            OrderStatus orderStatus = order.getOrderStatus();
            OrderProcessor orderProcessor = orderStatus.getProcessorForStatus(paymentRepository, dispatchService);
            orderProcessDto = orderProcessor.process(order);
            order = orderProcessDto.getProcessedOrder();
            ActionType actionType = orderProcessDto.getActionType();
            InformationService informationService = actionType.getInformationServiceForAction();
            informationService.notify(orderProcessDto);

        } while (orderProcessDto.orderReadyForProcessing());

        orderRepository.store(order);
    }

}
