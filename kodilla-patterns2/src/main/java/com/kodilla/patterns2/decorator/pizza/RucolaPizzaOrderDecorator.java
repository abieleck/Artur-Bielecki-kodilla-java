package com.kodilla.patterns2.decorator.pizza;

import java.math.BigDecimal;

public class RucolaPizzaOrderDecorator  extends AbstractPizzaOrderDecorator {
    protected RucolaPizzaOrderDecorator(PizzaOrder pizzaOrder) {
        super(pizzaOrder);
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal("2"));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", rucola";
    }
}