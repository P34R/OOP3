package com.example.carrentservlets.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class User {

    private long id;

    private String login;

    private String password;

    private String passport;

    private UserRole role;

    @Setter
    private BigDecimal funds;


    public User(String login, String password, String passport) {
        this.login = login;
        this.password = password;
        this.passport = passport;
        role = UserRole.USER;
        funds = new BigDecimal(0);
    };
    
    public User(long id, String login, String password, String passport, BigDecimal funds, UserRole role) {
    	this.id = id;
    	this.login = login;
    	this.password = password;
        this.passport = passport;
    	this.funds = funds;
    	this.role = role;
    }


}
