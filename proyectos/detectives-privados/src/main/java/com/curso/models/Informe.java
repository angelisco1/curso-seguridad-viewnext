package com.curso.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="informes")
public class Informe {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String titulo;
	private String descripcion;
	@Column(length = 4000)
	private String contenido;
	private String color;
	private Integer userId;
	
	
	public Informe() {}
	
	public Informe(String id, String titulo, String descripcion, String contenido, String color, Integer userId) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.contenido = contenido;
		this.color = color;
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Informe [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", contenido=" + contenido
				+ ", color=" + color + ", userId=" + userId + "]";
	}
	
}
