package com.kodilla.good.patterns.challenges.suppliers.supplier;

import java.util.HashMap;

public class SupplierSelector {

    private static HashMap<String, Supplier> suppliersByID = new HashMap<>();

    public static void registerNewSupplier (Supplier supplier) throws DuplicateSupplierException {

        if (suppliersByID.containsKey(supplier.getSupplierID())) {
            throw new DuplicateSupplierException(supplier.getSupplierID());
        }

        suppliersByID.put(supplier.getSupplierID(), supplier);
    }

    public static Supplier getSupplier(String supplierID) {
        return suppliersByID.get(supplierID);
    }

}
