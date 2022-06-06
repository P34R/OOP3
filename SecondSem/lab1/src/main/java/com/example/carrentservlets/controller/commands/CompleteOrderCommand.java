package com.example.carrentservlets.controller.commands;


import com.example.carrentservlets.constants.URL;
import com.example.carrentservlets.dao.CarDao;
import com.example.carrentservlets.dao.UserDao;
import com.example.carrentservlets.dao.impl.CarDaoImpl;
import com.example.carrentservlets.dao.impl.UserDaoImpl;
import com.example.carrentservlets.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.Instant;

public class CompleteOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if(user.getRole()==UserRole.ADMIN){
            long orderId = Long.valueOf(request.getParameter("order_id"));
            long userId = Long.valueOf(request.getParameter("user_id"));
            String fineStr = request.getParameter("fine");
            BigDecimal fine = BigDecimal.valueOf(Long.parseLong(fineStr.isEmpty() ? "0" : fineStr));


            UserDao userDao = new UserDaoImpl();
            Order order = userDao.getOrder(orderId);

            CarDao carDao = new CarDaoImpl();
            Car car = carDao.getCarById(order.getCarId());

            userDao.completeOrder(userId, orderId, fine);

            if(fine.equals(BigDecimal.ZERO))
                carDao.updateCarStatus((int) order.getCarId(), CarStatus.AVAILABLE);
            else
                carDao.updateCarStatus((int) order.getCarId(), CarStatus.BROKEN);

            Instant now = Instant.now();
            userDao.payForOrder(userId, fine, car.getPrice(), (int) ((now.getEpochSecond() - order.getStartDate())/60/60));
        }

    	return URL.REDIRECT_PROFILE;
    }
}
