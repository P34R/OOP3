package com.example.carrentservlets.dao.impl;

import com.example.carrentservlets.constants.Queries;
import com.example.carrentservlets.dao.UserDao;
import com.example.carrentservlets.model.Order;
import com.example.carrentservlets.model.User;
import com.example.carrentservlets.model.UserRole;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

	public long register(User user) {
		int result = 0;
		
        try (Connection connection = DBManager.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.USER_INSERT);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFunds().toString());
            preparedStatement.setString(4, user.getRole().toString());

            result = preparedStatement.executeUpdate();
			connection.commit();

			System.out.println("REGISTRATION SUCCESS");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
        }
        return result;
	}

	@Override
    public User getUserByLogin(String login) {
        User user = null;
        try (Connection connection = DBManager.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.USER_GET_BY_LOGIN);
            preparedStatement.setString(1, login);
            user = getUserFromPreparedStatement(preparedStatement);
			connection.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
        }
        return user;
    }

	@Override
	public Order getOrder(long id) {
		try (Connection connection = DBManager.getInstance().getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_ORDER)) {
			preparedStatement.setLong(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return new Order(
								resultSet.getLong("id"),
								resultSet.getLong("user_id"),
								resultSet.getLong("car_id"),
								resultSet.getLong("start_date"),
								resultSet.getLong("end_date"));
			}
			resultSet.close();
			connection.commit();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Order> getUserOrders(long id) {
		List<Order> subscriptions = new ArrayList<>();
		try (Connection connection = DBManager.getInstance().getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(Queries.USER_GET_ORDERS)) {
	            preparedStatement.setLong(1, id);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {
	                subscriptions.add(
	                		new Order(
									resultSet.getLong("id"),
									resultSet.getLong("user_id"),
									resultSet.getLong("car_id"),
									resultSet.getLong("start_date"),
									resultSet.getLong("end_date"))
	                		);
	            }
	            resultSet.close();
	            connection.commit();

	        } catch (SQLException e) {
				System.out.println(e.getMessage());
        	}
		return subscriptions;
	}

	@Override
	public List<Order> getAllActiveOrders() {
		List<Order> subscriptions = new ArrayList<>();
		try (Connection connection = DBManager.getInstance().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(Queries.USER_ACTIVE_ORDERS)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				subscriptions.add(
						new Order(
								resultSet.getLong("id"),
								resultSet.getLong("user_id"),
								resultSet.getLong("car_id"),
								resultSet.getLong("start_date"),
								resultSet.getLong("end_date"))
				);
			}
			resultSet.close();
			connection.commit();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return subscriptions;
	}
	
	public void makeOrder(long userId, long carId, BigDecimal price) {
		try (Connection connection = DBManager.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.MAKE_ORDER);
            preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, carId);
			preparedStatement.setBigDecimal(3, price);
            preparedStatement.setLong(4, Instant.now().getEpochSecond());

            preparedStatement.executeUpdate();
			connection.commit();

			System.out.println("ADDED ORDER");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
        }
	}
	
	public void completeOrder(long userId, long orderId, BigDecimal price) {
		try (Connection connection = DBManager.getInstance().getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(Queries.COMPLETE_ORDER)) {
			preparedStatement.setLong(1, Instant.now().getEpochSecond());
			preparedStatement.setLong(2, orderId);

			preparedStatement.executeUpdate();
			connection.commit();

			System.out.println("ORDER COMPLETED");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
        }
	}

	public void payForOrder(long userId, BigDecimal fine, BigDecimal price, int hoursBetween) {
		try (Connection connection = DBManager.getInstance().getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(Queries.USER_SUBTRACT_FUNDS)) {
			preparedStatement.setBigDecimal(1, price.multiply(new BigDecimal(hoursBetween)).add(fine));
			preparedStatement.setLong(2, userId);

			preparedStatement.executeUpdate();
			connection.commit();

			System.out.println("USER FUNDS SUBTRACTED");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addFunds(long userId, BigDecimal value) {
		try (Connection connection = DBManager.getInstance().getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(Queries.USER_ADD_FUNDS)) {
            	preparedStatement.setBigDecimal(1, value);
	            preparedStatement.setLong(2, userId);

	            preparedStatement.executeUpdate();

			System.out.println("ADDED FUNDS");
        } catch (SQLException e) {
			System.out.println(e.getMessage());
        }
	}

	public List<User> getDebtors() {
		List<User> users = new ArrayList<>();
		try (Connection connection = DBManager.getInstance().getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(Queries.USER_GET_DEBTORS);
			users = getUsersFromPreparedStatement(preparedStatement);
			connection.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return users;
	}

	// -------  UTILS  --------
	
	private User getUserFromPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        User user = null;
        try(ResultSet resultSet = preparedStatement.executeQuery()){
        	if (resultSet.next()) {
				System.out.println(resultSet.getStatement());
                user = new User(
						resultSet.getLong("id"),
						resultSet.getString("login"),
						resultSet.getString("password"),
						resultSet.getString("passport"),
						resultSet.getBigDecimal("funds"),
						UserRole.valueOf(resultSet.getString("role")));
            }
        }
        return user;
    }


	private List<User> getUsersFromPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
		List<User> users = new ArrayList<>();
		try(ResultSet resultSet = preparedStatement.executeQuery()){
			if (resultSet.next()) {
				System.out.println(resultSet.getStatement());
				users.add(new User(
						resultSet.getLong("id"),
						resultSet.getString("login"),
						resultSet.getString("password"),
						resultSet.getString("passport"),
						resultSet.getBigDecimal("funds"),
						UserRole.valueOf(resultSet.getString("role"))));
			}
		}
		return users;
	}
}