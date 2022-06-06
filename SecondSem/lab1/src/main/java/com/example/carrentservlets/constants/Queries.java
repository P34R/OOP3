package com.example.carrentservlets.constants;

public class Queries {
	
	public static final String CAR_INSERT = "INSERT INTO `car` (`model`, `color`, `price`, `status`) VALUES (?, ?, ?, ?);";
	public static final String CAR_UPDATE = "UPDATE car SET model=?, color=?, price=?, status=? WHERE id=?;";

	public static final String CAR_UPDATE_STATUS = "UPDATE car SET status=? WHERE id=?;";
	public static final String CAR_DELETE = "DELETE FROM car WHERE id=?;";
	public static final String CAR_GET_BY_ID = "SELECT * FROM car WHERE id=?;";
	public static final String CAR_GET_BY_STATUS = "SELECT * FROM car WHERE status=?";
	
	public static final String USER_INSERT = "INSERT INTO user (login, password, funds, passport, role) VALUES (?, ?, ?, ?, ?);";
	public static final String USER_GET_BY_LOGIN = "SELECT * FROM user WHERE login=?";
	public static final String USER_GET_ORDERS = "SELECT * FROM `order` WHERE user_id=? AND end_date IS NULL";

	public static final String USER_ACTIVE_ORDERS = "SELECT * FROM `order` WHERE end_date IS NULL";
	public static final String MAKE_ORDER = "INSERT INTO `order` (user_id, car_id, price, start_date) VALUES (?, ?, ?, ?);";

	public static final String GET_ORDER = "SELECT * FROM `order` WHERE id=?";
	public static final String COMPLETE_ORDER = "UPDATE `order` SET order.end_date = ? WHERE id=?;";

	public static final String USER_SUBTRACT_FUNDS = "UPDATE user SET user.funds=user.funds-? WHERE id=?;";
	public static final String USER_ADD_FUNDS = "UPDATE user SET user.funds=user.funds+? WHERE id=?;";
    public static final String USER_GET_DEBTORS = "SELECT * FROM user WHERE funds<0";
}
    