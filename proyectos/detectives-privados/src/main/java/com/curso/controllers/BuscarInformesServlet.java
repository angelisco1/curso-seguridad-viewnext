package com.curso.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.EncodingException;

import com.curso.models.Informe;
import com.curso.utils.DatabaseUtil;

@WebServlet("/authenticated/buscar-informes")
public class BuscarInformesServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String busqueda = req.getParameter("busqueda");
		String busquedaSaneada = null;
		
		
		try {
			busquedaSaneada = ESAPI.encoder().encodeForURL(busqueda);
		} catch (EncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<Informe> informes = new ArrayList<>();
		
		Connection conn = null;
		
		try {
			
			conn = DatabaseUtil.getConnection();
			
			String sql = "SELECT * FROM informes WHERE titulo LIKE ? OR contenido LIKE ? OR descripcion LIKE ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, "%"+busquedaSaneada+"%");
			pst.setString(2, "%"+busquedaSaneada+"%");
			pst.setString(3, "%"+busquedaSaneada+"%");
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String contenido = rs.getString("contenido");
				String color = rs.getString("color");
				Integer userId = rs.getInt("userId");
				
				Informe informe = new Informe(id, titulo, descripcion, contenido, color, userId);
				informes.add(informe);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeConnection(conn);
		}
		
				
		req.setAttribute("informes", informes);
		req.setAttribute("busqueda", busquedaSaneada);
		req.getRequestDispatcher("informes-buscados.jsp").forward(req, resp);
		
	}
}
