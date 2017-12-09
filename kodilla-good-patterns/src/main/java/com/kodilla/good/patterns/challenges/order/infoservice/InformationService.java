package com.kodilla.good.patterns.challenges.order.infoservice;

import com.kodilla.good.patterns.challenges.order.processor.OrderProcessDto;

public interface InformationService {

    void notify(OrderProcessDto orderProcessDto);

}
