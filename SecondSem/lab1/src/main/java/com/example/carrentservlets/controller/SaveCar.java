package com.example.carrentservlets.controller;

import com.example.carrentservlets.constants.URL;
import com.example.carrentservlets.dao.CarDao;
import com.example.carrentservlets.dao.impl.CarDaoImpl;
import com.example.carrentservlets.model.Car;
import com.example.carrentservlets.model.CarStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class SaveCar extends HttpServlet {
	private static final long serialVersionUID = -5577566949314653715L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("remove")!=null) {
			CarDao dao = new CarDaoImpl();
			dao.delete(Long.valueOf(request.getParameter("remove")));
			System.out.println("Done removing car");
		}
		response.sendRedirect(URL.REDIRECT_HOME);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CarDao dao = new CarDaoImpl();
		String model = request.getParameter("model");
		String color = request.getParameter("color");
		BigDecimal price = new BigDecimal(request.getParameter("price"));

		if(request.getParameter("id")!=null) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.update(new Car(id, model, color, price, CarStatus.AVAILABLE));
			System.out.println("Done updating car");
        } else {
    		dao.insert(new Car(model, color, price, CarStatus.AVAILABLE));
			System.out.println("Done adding car");
        }
		doGet(request, response);
	}

	
}
