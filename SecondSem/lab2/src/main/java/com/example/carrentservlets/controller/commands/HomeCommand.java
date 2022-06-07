package com.example.carrentservlets.controller.commands;

import com.example.carrentservlets.constants.*;
import com.example.carrentservlets.dao.*;
import com.example.carrentservlets.dao.impl.*;
import com.example.carrentservlets.model.*;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
    	CarDao dao = new CarDaoImpl();

        CarStatus status;
        if(request.getParameter("menutype") == null)
            status = CarStatus.AVAILABLE;
        else {
            status = CarStatus.valueOf(request.getParameter("menutype"));
            request.getSession().setAttribute("invalidData", "false");
        }

        request.setAttribute("menutype", status.name());
        request.setAttribute("data", dao.getCarsWithStatus(status));

		return URL.HOME;
    }

}
