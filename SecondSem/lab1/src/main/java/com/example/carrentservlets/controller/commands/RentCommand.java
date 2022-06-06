package com.example.carrentservlets.controller.commands;

import com.example.carrentservlets.constants.*;
import com.example.carrentservlets.dao.*;
import com.example.carrentservlets.dao.impl.*;
import com.example.carrentservlets.model.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class RentCommand implements Command {
	
    @Override
    public String execute(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        long carId = Long.parseLong(request.getParameter("car_id"));
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(request.getParameter("price")));

        UserDao userDao = new UserDaoImpl();
        userDao.makeOrder(user.getId(), carId, price);

        CarDao carDao = new CarDaoImpl();
        carDao.updateCarStatus((int) carId, CarStatus.RENTED);

    	return URL.REDIRECT_PROFILE;
    }
}
