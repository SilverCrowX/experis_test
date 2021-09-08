package com.experis.test.carritodecompras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.experis.test.carritodecompras.entity.DetalleCompraCarrito;
import com.experis.test.carritodecompras.entity.Producto;
import com.experis.test.carritodecompras.service.CarritoDeCompraService;

@RestController
@RequestMapping("/experis")
public class CarritoDeCompras {

	@Autowired
	CarritoDeCompraService cdcs;

	@CrossOrigin
	@GetMapping("/buscarPorNombre")
	public List<Producto> buscarPorNombre(String nombre, int pagina, int elementos) {
		return cdcs.buscarPorNombre(nombre, pagina, elementos);
	}

	@CrossOrigin
	@GetMapping("/buscarPorPrecios")
	public List<Producto> buscarPorPrecios(int valor1, int valor2, int pagina, int elementos) {
		return cdcs.buscarPorPrecios(valor1, valor2, pagina, elementos);
	}

	@CrossOrigin
	@GetMapping("/buscarPorMarca")
	public List<Producto> buscarPorMarca(String marca, int pagina, int elementos) {
		return cdcs.buscarPorMarca(marca, pagina, elementos);
	}

	@CrossOrigin
	@GetMapping("/agregarProducto")
	public String agregarProducto(Integer identificacion, Integer producto, Integer cantidad) {
		return cdcs.agregarProducto(identificacion, producto, cantidad);
	}

	@CrossOrigin
	@GetMapping("/consultarCarritoPorUsuario")
	public List<DetalleCompraCarrito> consultarCarritoPorUsuario(Integer identificacion) {
		return cdcs.consultarCarritoPorUsuario(identificacion);
	}

	@CrossOrigin
	@GetMapping("/vaciarCarrito")
	public String vaciarCarrito(Integer identificacion) {
		return cdcs.vaciarCarrito(identificacion, false);
	}

	@CrossOrigin
	@GetMapping("/confirmarCompra")
	public String confirmarCompra(Integer identificacion) {
		return cdcs.confirmarCompra(identificacion);
	}
	
}
