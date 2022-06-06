package com.example.carrentservlets.controller.commands;

import java.util.Map;
import java.util.TreeMap;

public class CommandHandler{

	private static final Map<String, Command> commands = new TreeMap<>();

	static {
	    commands.put("home", new HomeCommand());
	    commands.put("login", new LoginCommand());
	    commands.put("registration", new RegistrationCommand());
	    commands.put("logout", new LogoutCommand());
	    commands.put("profile", new MyProfileCommand());
	    commands.put("rent", new RentCommand());
	    commands.put("completeOrder", new CompleteOrderCommand());
	    commands.put("addFunds", new AddFundsCommand());
		commands.put("edit", new EditCommand());
		commands.put("fix", new FixCarCommand());
		commands.put("transaction", new TransactionCommand());
	}
	
	private CommandHandler() {}
	
	public static Command get(String commandName) {
	    if (commandName == null) return commands.get("home");
	    if (commands.containsKey(commandName)) {
	        return commands.get(commandName);
	    }
	    return null;
	}
	
	public static boolean contains(String commandName) {
	    return commands.get(commandName) != null;
	}

}
