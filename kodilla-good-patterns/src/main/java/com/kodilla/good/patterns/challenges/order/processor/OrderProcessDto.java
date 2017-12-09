package com.kodilla.good.patterns.challenges.order.processor;

import com.kodilla.good.patterns.challenges.order.orders.Order;

public final class OrderProcessDto {

    private final Order order;
    private final boolean readyForFurtherProcessing;
    private final ActionType actionType;

    public OrderProcessDto(final Order order, final boolean readyForFurtherProcessing, final ActionType actionType) {
        this.order = order;
        this.readyForFurtherProcessing = readyForFurtherProcessing;
        this.actionType = actionType;
    }

    public Order getProcessedOrder() {
        return order;
    }

    public boolean orderReadyForProcessing() {
        return this.readyForFurtherProcessing;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
