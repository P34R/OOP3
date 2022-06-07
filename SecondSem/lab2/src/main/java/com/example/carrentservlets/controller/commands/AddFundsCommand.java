package com.example.carrentservlets.controller.commands;


import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import com.example.carrentservlets.constants.*;
import com.example.carrentservlets.dao.*;
import com.example.carrentservlets.dao.impl.*;
import com.example.carrentservlets.model.*;

public class AddFundsCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		UserDao dao = new UserDaoImpl();
		User user = (User)request.getSession().getAttribute("user");
		BigDecimal fundsToAdd = new BigDecimal(request.getParameter("funds"));
		user.setFunds(user.getFunds().add(fundsToAdd));
		dao.addFunds(user.getId(), fundsToAdd);
		return URL.REDIRECT_PROFILE;
		
		
	}

}
