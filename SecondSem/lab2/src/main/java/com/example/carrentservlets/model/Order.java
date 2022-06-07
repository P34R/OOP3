package com.example.carrentservlets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Order {
	private final long id;
	private final long userId;
    private final long carId;
    private final Long startDate;
    private final Long endDate;
}
