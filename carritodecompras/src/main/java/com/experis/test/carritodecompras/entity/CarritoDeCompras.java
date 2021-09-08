package com.experis.test.carritodecompras.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CARRITO_DE_COMPRAS")
@Data
public class CarritoDeCompras {

	@Id
	@Column(name = "CARRITO_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer carritoId;

	@Column(name = "USUARIO_ID", unique = true, nullable = false)
	private Integer usuarioId;

	@Column(name = "FECHA_COMPRA", nullable = false)
	private Timestamp fechaCompra;

}
