package com.example.carrentservlets.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class OrderDto {

    private final long orderId;
    private final long userId;
    private final String model;
    private final String color;
    private final BigDecimal price;
    private final CarStatus status;
    private final Instant startDate;

    public OrderDto(Order order, Car car){
        this.orderId = order.getId();
        this.userId = order.getUserId();
        this.model = car.getModel();
        this.color = car.getColor();
        this.price = car.getPrice();
        this.status = car.getStatus();
        this.startDate = Instant.ofEpochSecond(order.getStartDate());
    }
}
