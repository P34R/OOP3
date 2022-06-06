package com.example.carrentservlets.controller.commands;


import com.example.carrentservlets.constants.URL;
import com.example.carrentservlets.dao.CarDao;
import com.example.carrentservlets.dao.impl.CarDaoImpl;
import com.example.carrentservlets.model.Car;

import javax.servlet.http.HttpServletRequest;

public class EditCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		CarDao dao = new CarDaoImpl();
		
		if(request.getParameter("id")!=null) {
			Car car = dao.getCarById(Integer.valueOf(request.getParameter("id")));
			request.setAttribute("car", car);
		}

		return URL.CAR;
	}

}
