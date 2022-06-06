package com.example.carrentservlets.controller.commands;

import javax.servlet.http.HttpServletRequest;

public interface Command{
	String execute(HttpServletRequest request);
}