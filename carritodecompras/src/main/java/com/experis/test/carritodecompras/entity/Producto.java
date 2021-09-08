package com.experis.test.carritodecompras.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "PRODUCTO")
@Data
public class Producto {

	@Id
	@Column(name = "PRODUCTO_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productoId;

	@Column(name = "NOMBRE", unique = true, nullable = false)
	private String nombre;

	@Column(name = "MARCA", nullable = false)
	private String marca;

	@Column(name = "PRECIO", nullable = false)
	private Integer precio;

	@Column(name = "CANTIDAD", nullable = false)
	private Integer cantidad;

	@Column(name = "ESTADO", nullable = false)
	private String estado;

	@Column(name = "DESCUENTO", nullable = false)
	private Double descuento;

}
