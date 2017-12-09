package com.kodilla.good.patterns.challenges.suppliers.order;

import com.kodilla.good.patterns.challenges.suppliers.order.Item;
import com.kodilla.good.patterns.challenges.suppliers.order.Order;
import com.kodilla.good.patterns.challenges.suppliers.order.OrderProvider;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class OrderProviderCsv implements OrderProvider {

    private final static String ORDERS_FILENAME = "file/OrdersSuppliers.csv";

    Stream<Order> orders;

    public OrderProviderCsv() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(ORDERS_FILENAME).getFile());
        Path path = Paths.get(file.getPath());
        try {
            orders = Files.lines(path)
                    .skip(1)
                    .map(this::readOrderFromString);
        } catch (IOException e) {
            e.printStackTrace();
            orders = Stream.empty();
        }
    }

    private Order readOrderFromString(String csvLine) {

        String[] columns = csvLine.split(";");
        String supplierID = columns[0];
        Item item = new Item(columns[1]);
        BigDecimal quantity = new BigDecimal(columns[2]);
        Order order = new Order(supplierID, item, quantity);
        return order;

    }

    @Override
    public Stream<Order> getOrders() {
        return orders;
    }
}
