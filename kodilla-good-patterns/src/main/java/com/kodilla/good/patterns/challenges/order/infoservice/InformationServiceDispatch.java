package com.kodilla.good.patterns.challenges.order.infoservice;

import com.kodilla.good.patterns.challenges.order.orders.Order;
import com.kodilla.good.patterns.challenges.order.processor.OrderProcessDto;
import com.kodilla.good.patterns.challenges.order.orders.User;

public class InformationServiceDispatch implements InformationService {

    @Override
    public void notify(OrderProcessDto orderProcessDto) {

        Order order = orderProcessDto.getProcessedOrder();
        User user = order.getUser();

        String messageText = "Order No " + order.getOrderId() + " was sent to " + user.getAddress();
        System.out.println("SMS to " + user.getMobileNumber() + " (" + user.getName() + "): " + messageText);
        System.out.println("E-mail to " + user.getEmailAddress() + " (" + user.getName() + "): " + messageText);

    }
}
