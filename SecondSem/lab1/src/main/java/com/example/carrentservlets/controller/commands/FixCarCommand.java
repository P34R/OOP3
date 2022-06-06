package com.example.carrentservlets.controller.commands;


import com.example.carrentservlets.constants.URL;
import com.example.carrentservlets.dao.CarDao;
import com.example.carrentservlets.dao.impl.CarDaoImpl;
import com.example.carrentservlets.model.Car;
import com.example.carrentservlets.model.CarStatus;

import javax.servlet.http.HttpServletRequest;

public class FixCarCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		CarDao dao = new CarDaoImpl();
		int id = Integer.parseInt(request.getParameter("id"));
		dao.updateCarStatus(id, CarStatus.AVAILABLE);

		return URL.HOME;
	}

}
