package com.kodilla.good.patterns.challenges.suppliers;

import com.kodilla.good.patterns.challenges.suppliers.order.Order;
import com.kodilla.good.patterns.challenges.suppliers.supplier.Supplier;
import com.kodilla.good.patterns.challenges.suppliers.supplier.SupplierSelector;

public class OrderProcessor {

    public boolean processOrder(Order order) {

        String supplierID = order.getSupplierID();
        Supplier supplier = SupplierSelector.getSupplier(supplierID);
        boolean orderCompleted = supplier.process(order.getItem(), order.getQuantity());
        return orderCompleted;

    }
}
