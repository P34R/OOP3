package com.example.carrentservlets.controller.commands;


import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.time.Instant;

import com.example.carrentservlets.constants.*;
import com.example.carrentservlets.dao.*;
import com.example.carrentservlets.dao.impl.*;
import com.example.carrentservlets.model.*;

public class TransactionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("Tx tx tx");
        Connection connection=null;
        Car example_car = new Car("transaction", "red", new BigDecimal(1234), CarStatus.RENTED);
        Savepoint sv=null;
        try {
            connection = DBManager.getInstance().getConnection();
            sv=connection.setSavepoint();
        }catch (SQLException e){

            System.out.println(e.getSQLState());
            return null;
        }
        try (PreparedStatement firstStatement = connection.prepareStatement(Queries.CAR_INSERT);
             PreparedStatement secondStatement = connection.prepareStatement(Queries.MAKE_ORDER))
             {

                 firstStatement.setString(1, example_car.getModel());
                 firstStatement.setString(2, example_car.getColor());
                 firstStatement.setBigDecimal(3, example_car.getPrice());
                 firstStatement.setString(4, CarStatus.RENTED.name());

                 secondStatement.setLong(1, 8);
                 secondStatement.setLong(2, 20);
                 secondStatement.setBigDecimal(3, BigDecimal.valueOf(500));
                 secondStatement.setLong(4, Instant.now().getEpochSecond());

                 firstStatement.executeUpdate();
                 secondStatement.executeUpdate();

                 connection.commit();
                 System.out.println("Tx done");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback(sv);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return URL.REDIRECT_PROFILE;


    }

}
