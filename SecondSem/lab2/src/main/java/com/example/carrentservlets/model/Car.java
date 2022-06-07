package com.example.carrentservlets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Car {
	private long id;
	private String model;
	private String color;
	private BigDecimal price;

	private CarStatus status;

	public Car(String model, String color, BigDecimal price, CarStatus status){
		this.model = model;
		this.color = color;
		this.price = price;
		this.status = status;
	}

	@Override
	public String toString() {
		return id+"[\n"
				+ "\tModel: " + model
				+ "\n\tColor: " + color
				+ "\n\tPrice: " + price.toPlainString()
				+ "\n\tStatus: " + status.name()
				+ "\n]";
	}




}
