package com.example.carrentservlets.dao.impl;

import com.example.carrentservlets.constants.Queries;
import com.example.carrentservlets.dao.CarDao;
import com.example.carrentservlets.model.Car;
import com.example.carrentservlets.model.CarStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {
	public int insert(Car car) {
		int result = 0;

        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.CAR_INSERT)) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setString(2, car.getColor());
            preparedStatement.setBigDecimal(3, car.getPrice());
            preparedStatement.setString(4, CarStatus.AVAILABLE.name());

            result = preparedStatement.executeUpdate();
            connection.commit();
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
	}
	
	public void update(Car car) {
        try (Connection connection = DBManager.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.CAR_UPDATE);
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setString(2, car.getColor());
            preparedStatement.setBigDecimal(3, car.getPrice());
            preparedStatement.setString(4, car.getStatus().name());
            preparedStatement.setLong(5, car.getId());
            
            preparedStatement.executeUpdate();
            connection.commit();
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
		}
	}


    public void updateCarStatus(int id, CarStatus status){
        try (Connection connection = DBManager.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.CAR_UPDATE_STATUS);
            preparedStatement.setString(1, status.name());
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(long id) {
        try (Connection connection = DBManager.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.CAR_DELETE);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
		}
	}

	
	public Car getCarById(long id) {
		Car car = null;
		
        try (Connection connection = DBManager.getInstance().getConnection()){   	
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.CAR_GET_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            car = getcar(resultSet);
            resultSet.close();

            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return car;
	}
	
	public List<Car> getCarsWithStatus(CarStatus status) {
		List<Car> cars = null;

        try (Connection connection = DBManager.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.CAR_GET_BY_STATUS);
            preparedStatement.setString(1, status.name());

            ResultSet resultSet = preparedStatement.executeQuery();
            cars = getCarList(resultSet);
            
            resultSet.close();

            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cars;
	}

	public List<Car> getSortedCarList(String sort) {
		List<Car> cars = null;

        try (Connection connection = DBManager.getInstance().getConnection()){
        	
        	//using preparedStatement would add extra quotes which will result in incorrect results
        	String SELECT_QUERY ="SELECT * FROM car ORDER BY "+sort+" ASC;";

            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_QUERY);
            cars = getCarList(resultSet);
            resultSet.close();
            connection.commit();

        } catch (SQLException e) {
        }
        return cars;
	}
        
    // -------  UTILS  ---------
	
    private List<Car> getCarList(ResultSet resultSet) throws SQLException {
        List<Car> cars = new ArrayList<>();
        while (resultSet.next()) {
        	Car car = new Car(resultSet.getInt("id"),
                    resultSet.getString("model"),
                    resultSet.getString("color"),
                    resultSet.getBigDecimal("price"),
                    CarStatus.valueOf(resultSet.getString("status")));
        	cars.add(car);
        }
        return cars;
    }

    private Car getcar(ResultSet resultSet) throws SQLException {
        List<Car> rooms = getCarList(resultSet);
        if (!rooms.isEmpty()) return rooms.get(0);
        return null;
    }
        
}
