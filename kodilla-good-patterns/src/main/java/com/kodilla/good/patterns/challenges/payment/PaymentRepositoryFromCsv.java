package com.kodilla.good.patterns.challenges.payment;

import com.kodilla.good.patterns.challenges.order.Order;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.stream.Stream;

public class PaymentRepositoryFromCsv implements PaymentRepository {

    private HashMap<Long, Payment> paymentsByOrderId = new HashMap<>();

    public PaymentRepositoryFromCsv() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("file/Payments.csv").getFile());
        Path path = Paths.get(file.getPath());
        try {
            Stream<String> fileLines = Files.lines(path).skip(1);
            fileLines.forEach(this::addPaymentEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPaymentEntry(String fileLine) {

        String[] columns = fileLine.split(";");
        long orderId = Long.parseLong(columns[0]);
        String[] dateFields = columns[1].split("-");
        int year = Integer.parseInt(dateFields[0]);
        int month = Integer.parseInt(dateFields[1]);
        int day = Integer.parseInt(dateFields[2]);
        LocalDate paymentDate = LocalDate.of(year, month, day);
        Payment payment = new Payment(orderId, paymentDate);
        paymentsByOrderId.put(orderId, payment);

    }

    @Override
    public Payment getPaymentForOrder(Order order) {

        return paymentsByOrderId.get(order.getOrderId());

    }
}
