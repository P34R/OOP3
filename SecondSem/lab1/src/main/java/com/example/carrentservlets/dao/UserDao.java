package com.example.carrentservlets.dao;


import com.example.carrentservlets.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao {

    long register(User user);
    User getUserByLogin(String login);
	List<Order> getUserOrders(long id);

	public List<Order> getAllActiveOrders();

	public void makeOrder(long userId, long carId, BigDecimal price);
	public void completeOrder(long userId, long orderId, BigDecimal fine);
	public Order getOrder(long id);

	public void payForOrder(long userId, BigDecimal fine, BigDecimal price, int hours);
	public void addFunds(long userId, BigDecimal value);

    public List<User> getDebtors();
}
