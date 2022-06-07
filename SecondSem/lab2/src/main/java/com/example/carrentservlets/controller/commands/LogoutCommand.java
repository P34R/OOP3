package com.example.carrentservlets.controller.commands;

import com.example.carrentservlets.constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return URL.REDIRECT_HOME;
    }
}
