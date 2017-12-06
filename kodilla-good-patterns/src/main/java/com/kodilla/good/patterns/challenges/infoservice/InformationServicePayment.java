package com.kodilla.good.patterns.challenges.infoservice;

import com.kodilla.good.patterns.challenges.Order;
import com.kodilla.good.patterns.challenges.OrderProcessDto;
import com.kodilla.good.patterns.challenges.User;
import com.kodilla.good.patterns.challenges.infoservice.InformationService;

public class InformationServicePayment implements InformationService {

    @Override
    public void notify(OrderProcessDto orderProcessDto) {

        Order order = orderProcessDto.getProcessedOrder();
        User user = order.getUser();

        String messageText = "We have received payment for order " + order.getOrderId()
                + " The order is being prepared for dispatch.";
        System.out.println("E-mail to " + user.getEmailAddress() + " (" + user.getName() + "): " + messageText);
    }
}
