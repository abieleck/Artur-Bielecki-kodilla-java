package com.kodilla.good.patterns.challenges.processor;

import com.kodilla.good.patterns.challenges.infoservice.InformationService;
import com.kodilla.good.patterns.challenges.infoservice.InformationServiceDispatch;
import com.kodilla.good.patterns.challenges.infoservice.InformationServicePayment;

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
