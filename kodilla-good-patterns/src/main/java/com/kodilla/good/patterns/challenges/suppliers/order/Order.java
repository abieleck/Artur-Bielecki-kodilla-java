package com.kodilla.good.patterns.challenges.suppliers.order;

import com.kodilla.good.patterns.challenges.suppliers.order.Item;

import java.math.BigDecimal;

public final class Order {

    private final String supplierID;
    private final Item item;
    private final BigDecimal quantity;

    public Order(String supplierID, Item item, BigDecimal quantity) {
        this.supplierID = supplierID;
        this.item = item;
        this.quantity = quantity;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public Item getItem() {
        return item;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }
}
