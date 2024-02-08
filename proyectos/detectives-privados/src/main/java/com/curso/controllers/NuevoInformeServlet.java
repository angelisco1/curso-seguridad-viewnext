package com.curso.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;

import com.curso.models.User;
import com.curso.utils.DatabaseUtil;

@WebServlet("/authenticated/nuevo-informe")
public class NuevoInformeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		
		String token = UUID.randomUUID().toString();
		session.setAttribute("tokenCSRF", token);
		
		req.getRequestDispatcher("nuevo-informe.jsp").forward(req, resp);
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String titulo = req.getParameter("titulo");
		String descripcion = req.getParameter("descripcion");
		String contenido = req.getParameter("contenido");
		String color = req.getParameter("color");
		
		String formToken = req.getParameter("tokenCSRF");
		String sessionToken = null;
		Integer userId = null;
		
		HttpSession session = req.getSession(false);
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				userId = user.getId();
			}
			sessionToken = (String) session.getAttribute("tokenCSRF");
		}
		
		if (sessionToken == null || formToken == null || !sessionToken.equals(formToken)) {
			System.out.println("[!] CSRF DETECTADO");
			resp.sendRedirect("../login.html");
			return;
		}
		
		String tituloSaneado = ESAPI.encoder().encodeForHTML(titulo);
		String descripcionSaneada = ESAPI.encoder().encodeForHTML(descripcion);
		String contenidoSaneado = ESAPI.encoder().encodeForHTML(contenido);
		String colorSaneado = ESAPI.encoder().encodeForHTMLAttribute(color);
		
		
		Connection conn = null;
		
		try {
			conn = DatabaseUtil.getConnection();
					
			String sql = "INSERT INTO informes (titulo, descripcion, contenido, color, userId) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setString(1, titulo);
//			pst.setString(2, descripcion);
//			pst.setString(3, contenido);
			pst.setString(1, tituloSaneado);
			pst.setString(2, descripcionSaneada);
			pst.setString(3, contenidoSaneado);
			pst.setString(4, colorSaneado);
			pst.setInt(5, userId);
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeConnection(conn);
		}
		
		resp.sendRedirect("informes");
		
	}
	
	
}
