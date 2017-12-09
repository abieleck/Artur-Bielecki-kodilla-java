package com.kodilla.good.patterns.challenges.suppliers.supplier;

import com.kodilla.good.patterns.challenges.suppliers.order.Item;

import java.math.BigDecimal;
import java.util.Arrays;

public class GlutenFreeShopSupplier implements Supplier {
    @Override
    public String getSupplierID() {
        return "GlutenFreeShop";
    }

    @Override
    public boolean process(Item item, BigDecimal quantity) {
        if(Arrays.asList("Pszenica", "Chleb", "Maka").contains(item.getName())) {
            System.out.println("Gluten Free Shop refuses to deliver " + item + " - item contains gluten.");
            return false;
        } else {
            System.out.println("Gluten free shop delivers " + quantity + " of " + item);
            return true;
        }
    }
}
