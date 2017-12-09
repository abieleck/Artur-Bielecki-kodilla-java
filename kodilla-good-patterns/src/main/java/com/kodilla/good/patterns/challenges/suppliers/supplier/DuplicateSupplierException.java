package com.kodilla.good.patterns.challenges.suppliers.supplier;

public class DuplicateSupplierException extends Exception {

    public DuplicateSupplierException(String supplierID) {
        super(supplierID);
    }
}
