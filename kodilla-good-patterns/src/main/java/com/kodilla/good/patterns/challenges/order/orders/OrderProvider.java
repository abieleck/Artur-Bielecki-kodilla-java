package com.kodilla.good.patterns.challenges.order.orders;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class OrderProvider {

    private List<Order> orders = new ArrayList<>();

    public OrderProvider() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("file/Orders.csv").getFile());
        Path path = Paths.get(file.getPath());
        try {
            Stream<String> fileLines = Files.lines(path).skip(1);
            fileLines.forEach(this::addOrderEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stream<Order> getOrders() {
        return orders.stream();
    }

    private void addOrderEntry(String fileLine) {
        String[] columns = fileLine.split(";");
        long orderId = Long.parseLong(columns[0]);
        OrderStatus orderStatus = OrderStatus.valueOf(columns[1]);
        User user = new User(columns[2], columns[3], columns[4], columns[5]);

        List<OrderItem> orderItems = new ArrayList<>();
        int i = 6;
        while (i < columns.length && !"".equals(columns[i])) {
            Item item = new Item(columns[i]);
            int quantity = Integer.parseInt(columns[i+1]);
            BigDecimal price = new BigDecimal(columns[i+2]);
            OrderItem orderItem= new OrderItem(item, quantity, price);
            orderItems.add(orderItem);
            i += 3;
        }
        Order order = new Order(orderId, user, orderItems, orderStatus);
        orders.add(order);
    }
}
