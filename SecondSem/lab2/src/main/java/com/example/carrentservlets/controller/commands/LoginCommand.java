package com.example.carrentservlets.controller.commands;


import com.example.carrentservlets.constants.*;
import com.example.carrentservlets.dao.*;
import com.example.carrentservlets.dao.impl.UserDaoImpl;
import com.example.carrentservlets.model.*;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByLogin(login);

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
        	session.setAttribute("invalidData", "false");
            session.setAttribute("user", user);
        } else {
        	session.setAttribute("invalidData", "true");
        }
        return URL.REDIRECT_HOME;
    }
}
