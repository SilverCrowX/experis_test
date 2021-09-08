package com.experis.test.carritodecompras.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "DETALLE_COMPRA_CARRITO")
@Data
public class DetalleCompraCarrito {

	@Id
	@Column(name = "DETALLE_CARRITO_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer detalleCarritoId;

	@ManyToOne
	@JoinColumn(name = "ID_CARRITO_DE_COMPRAS", nullable = false)
	private CarritoDeCompras idCarritoDeCompras;

	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO", nullable = false)
	private Producto producto;

	@Column(name = "CANTIDAD", nullable = false)
	private Integer cantidad;

	@Column(name = "VALOR", nullable = false)
	private Double valor;

}
