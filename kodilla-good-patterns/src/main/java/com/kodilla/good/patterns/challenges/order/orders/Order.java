package com.kodilla.good.patterns.challenges.order.orders;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Order {

    private long orderId;
    private final User user;
    private final List<OrderItem> orderedItems;
    private final OrderStatus orderStatus;

    public Order(long orderId, final User user, final List<OrderItem> orderedItems, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.user = user;
        this.orderedItems = orderedItems;
        this.orderStatus = orderStatus;
    }

    public long getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getOrderedItems() {
        return orderedItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", orderedItems={" +
                orderedItems.stream()
                        .map(OrderItem::toString)
                        .collect(Collectors.joining(";")) + "}" +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
