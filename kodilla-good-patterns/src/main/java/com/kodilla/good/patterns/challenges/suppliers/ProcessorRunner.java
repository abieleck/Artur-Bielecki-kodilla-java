package com.kodilla.good.patterns.challenges.suppliers;

import com.kodilla.good.patterns.challenges.suppliers.order.OrderProvider;
import com.kodilla.good.patterns.challenges.suppliers.order.OrderProviderCsv;
import com.kodilla.good.patterns.challenges.suppliers.supplier.*;

public class ProcessorRunner {

    private static void registerSuppliers() throws DuplicateSupplierException {
        SupplierSelector.registerNewSupplier(new ExtraFoodShopSupplier());
        SupplierSelector.registerNewSupplier(new HealthyShopSupplier());
        SupplierSelector.registerNewSupplier(new GlutenFreeShopSupplier());
    }

    public static void main(String[] args) {

        OrderProcessor orderProcessor = new OrderProcessor();
        OrderProvider orderProvider = new OrderProviderCsv();
        try {
            registerSuppliers();
        } catch (DuplicateSupplierException e) {
            e.printStackTrace();
        }
        orderProvider.getOrders()
                .forEach(orderProcessor::processOrder);

    }
}
