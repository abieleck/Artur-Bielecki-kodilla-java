package com.kodilla.good.patterns.challenges.order.processor;

import com.kodilla.good.patterns.challenges.order.dispatch.DispatchService;
import com.kodilla.good.patterns.challenges.order.orders.Order;
import com.kodilla.good.patterns.challenges.order.orders.OrderStatus;

public class PaidOrderProcessor implements OrderProcessor {

    private DispatchService dispatchService;

    public PaidOrderProcessor(DispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @Override
    public OrderProcessDto process(Order order) {

        boolean orderSent = dispatchService.sendOrder(order);

        OrderStatus newStatus;

        if(orderSent) {
            newStatus = OrderStatus.SENT;
        } else {
            newStatus = order.getOrderStatus();
        }

        Order processedOrder = new Order(order.getOrderId(), order.getUser(), order.getOrderedItems(), newStatus);
        return new OrderProcessDto(processedOrder, false, ActionType.DISPATCH);

    }



}
