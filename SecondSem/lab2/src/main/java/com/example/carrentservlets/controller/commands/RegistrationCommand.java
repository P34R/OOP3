package com.example.carrentservlets.controller.commands;

import com.example.carrentservlets.constants.*;
import com.example.carrentservlets.dao.*;
import com.example.carrentservlets.dao.impl.*;
import com.example.carrentservlets.model.*;

import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String passport = request.getParameter("passport");
        
        if(!login.matches(Regex.LOGIN_REGEX) || !password.matches(Regex.PASSWORD_REGEX)) {
            session.setAttribute("invalidData", "true");
        	return URL.REDIRECT_HOME;	
        }
    	session.setAttribute("invalidData", "false");
        
    	UserDao userDao = new UserDaoImpl();
    	
    	User user = new User(login, BCrypt.hashpw(password, BCrypt.gensalt()), passport);
    	System.out.println(login);

		userDao.register(user);
        session.setAttribute("user", user);
		
        return URL.REDIRECT_HOME;
    }
}
