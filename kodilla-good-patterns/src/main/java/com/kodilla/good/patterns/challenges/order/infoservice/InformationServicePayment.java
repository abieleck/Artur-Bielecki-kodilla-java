package com.kodilla.good.patterns.challenges.order.infoservice;

import com.kodilla.good.patterns.challenges.order.orders.Order;
import com.kodilla.good.patterns.challenges.order.processor.OrderProcessDto;
import com.kodilla.good.patterns.challenges.order.orders.User;

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
