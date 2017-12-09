package com.kodilla.good.patterns.challenges.suppliers.supplier;

import com.kodilla.good.patterns.challenges.suppliers.order.Item;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HealthyShopSupplier implements Supplier {
    @Override
    public String getSupplierID() {
        return "HealthyShop";
    }

    @Override
    public boolean process(Item item, BigDecimal quantity) {

        List<Item> unhealthyItems = Arrays.asList("Cola", "Frytki", "Kebab").stream()
                .map(Item::new)
                .collect(Collectors.toList());
        if (unhealthyItems.contains(item.getName())) {
            return false;
        } else {
            System.out.println("Healthy Shop is delivering " + item + ", quantity is " + quantity + ".");
            return true;
        }
    }
}
