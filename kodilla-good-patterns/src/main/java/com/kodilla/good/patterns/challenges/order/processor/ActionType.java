package com.kodilla.good.patterns.challenges.order.processor;

import com.kodilla.good.patterns.challenges.order.infoservice.InformationService;
import com.kodilla.good.patterns.challenges.order.infoservice.InformationServiceDispatch;
import com.kodilla.good.patterns.challenges.order.infoservice.InformationServicePayment;

import java.util.function.Supplier;

public enum ActionType {
    PAYMENT(InformationServicePayment::new),
    DISPATCH(InformationServiceDispatch::new);

    private Supplier<InformationService> informationServiceProvider;

    ActionType(Supplier<InformationService> informationServiceProvider) {
        this.informationServiceProvider = informationServiceProvider;
    }

    public InformationService getInformationServiceForAction() {

        return informationServiceProvider.get();

    }
}
