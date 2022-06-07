package com.example.carrentservlets.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static DBManager dbManager;

    private DBManager() {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
        	e1.printStackTrace();
		}
    }

    public static DBManager getInstance() {
        if (dbManager == null) dbManager = new DBManager();
        return dbManager;
    }

    public Connection getConnection() throws SQLException {
    	Connection conn = DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/OOP?characterEncoding=UTF-8", "root", "root"
                   );

        conn.setAutoCommit(false);

        System.out.println("Connected to database");
        return conn;
    }

    public void commitAndClose(Connection connection) {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void rollbackAndClose(Connection connection) {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}