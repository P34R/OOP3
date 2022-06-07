package com.example.carrentservlets.dao;

import java.util.List;

import com.example.carrentservlets.model.Car;
import com.example.carrentservlets.model.CarStatus;


public interface CarDao {

	public int insert(Car car);

	public Car getCarById(long id);

	public List<Car> getCarsWithStatus(CarStatus status);

	public void update(Car car);

	public void updateCarStatus(int id, CarStatus status);

	public void delete(long valueOf);

	public List<Car> getSortedCarList(String sort);

}
