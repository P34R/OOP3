package com.example.carrentservlets.controller.commands;

import com.example.carrentservlets.constants.*;
import com.example.carrentservlets.dao.*;
import com.example.carrentservlets.dao.impl.*;
import com.example.carrentservlets.model.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class MyProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        List<Order> userOrders;

        UserDao userDao = new UserDaoImpl();
        if(user.getRole()==UserRole.USER)
            userOrders = userDao.getUserOrders(user.getId());
        else
            userOrders = userDao.getAllActiveOrders();


        CarDao carDao = new CarDaoImpl();

        List<OrderDto> resultOrders = new ArrayList<>();
        for(Order order: userOrders) {
        	resultOrders.add(new OrderDto(order, carDao.getCarById(order.getCarId())));
        }
        
        request.setAttribute("orders", resultOrders);

        if(user.getRole()==UserRole.ADMIN){
            request.setAttribute("debts", userDao.getDebtors());

        }

		return URL.PROFILE;
    	
    }
}
