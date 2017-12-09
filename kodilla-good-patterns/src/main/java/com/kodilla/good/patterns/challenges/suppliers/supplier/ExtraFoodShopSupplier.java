package com.kodilla.good.patterns.challenges.suppliers.supplier;

import com.kodilla.good.patterns.challenges.suppliers.order.Item;

import java.math.BigDecimal;

public class ExtraFoodShopSupplier implements Supplier {

    @Override
    public String getSupplierID() {
        return "ExtraFoodShop";
    }

    @Override
    public boolean process(Item item, BigDecimal quantity) {
        if (quantity.compareTo(new BigDecimal("20")) > 0) {
            return false;
        } else {
            System.out.println("Extra Food Shop delivers " + quantity + " " + item
                    + ((quantity.compareTo(BigDecimal.ZERO) <= 1) ? "." : "s."));
            return true;
        }
    }
}
