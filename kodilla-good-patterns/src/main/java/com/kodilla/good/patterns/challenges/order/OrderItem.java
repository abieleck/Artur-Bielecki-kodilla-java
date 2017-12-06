package com.kodilla.good.patterns.challenges.order;

import java.math.BigDecimal;

public final class OrderItem {

    private final Item item;
    private int quantity;
    private BigDecimal price;

    public OrderItem(Item item, int quantity, BigDecimal price) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "item=" + item +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
