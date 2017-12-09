package com.kodilla.good.patterns.challenges.suppliers.supplier;

import com.kodilla.good.patterns.challenges.suppliers.order.Item;

import java.math.BigDecimal;

public interface Supplier {

    String getSupplierID();

    boolean process(Item item, BigDecimal quantity);

}
