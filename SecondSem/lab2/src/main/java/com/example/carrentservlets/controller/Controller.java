package com.example.carrentservlets.controller;

import com.example.carrentservlets.controller.commands.Command;
import com.example.carrentservlets.controller.commands.CommandHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = -2670825831853246200L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter("command");
		Command command = CommandHandler.get(commandName);
		String uri = command.execute(request);
		
		if (uri.contains("/controller")) {
			response.sendRedirect(uri);
        } else {
            request.getRequestDispatcher(uri).forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURL());
		doGet(request, response);
	}
	
}
