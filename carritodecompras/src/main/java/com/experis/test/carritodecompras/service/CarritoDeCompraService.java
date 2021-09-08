package com.experis.test.carritodecompras.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.experis.test.carritodecompras.entity.CarritoDeCompras;
import com.experis.test.carritodecompras.entity.DetalleCompraCarrito;
import com.experis.test.carritodecompras.entity.Producto;
import com.experis.test.carritodecompras.repoitory.CarritoDeComprasRepository;
import com.experis.test.carritodecompras.repoitory.DetalleCompraCarritoRepository;
import com.experis.test.carritodecompras.repoitory.ProductoRepository;
import com.experis.test.carritodecompras.utilidades.BLog;

@Service
public class CarritoDeCompraService {

	@Autowired
	ProductoRepository pr;

	@Autowired
	CarritoDeComprasRepository ccr;

	@Autowired
	DetalleCompraCarritoRepository dccr;

	public List<Producto> buscarTodos() {
		return (List<Producto>) pr.findAll();
	}

	public List<Producto> buscarPorNombre(String nombre, int pagina, int elementos) {
		Pageable p = PageRequest.of(pagina, elementos, Sort.by("nombre"));
		return pr.findByNombreContaining(nombre, p);
	}

	public List<Producto> buscarPorPrecios(int valor1, int valor2, int pagina, int elementos) {
		Pageable p = PageRequest.of(pagina, elementos, Sort.by("nombre"));
		return pr.findByPrecioBetween(valor1, valor2, p);
	}

	public List<Producto> buscarPorMarca(String marca, int pagina, int elementos) {
		Pageable p = PageRequest.of(pagina, elementos, Sort.by("nombre"));
		return pr.findByMarca(marca, p);
	}

	public String agregarProducto(Integer identificacion, Integer producto, Integer cantidad) {
		String confirmacion = "";
		if (agregarProductoCarrito(producto, cantidad)) {
			CarritoDeCompras cc = ccr.findByUsuarioId(identificacion);
			if (cc != null) {
				Producto p = pr.findById(producto).get();

				DetalleCompraCarrito dcc = dccr.findByIdCarritoDeComprasAndProducto(cc, p);

				if (dcc != null) {

					dcc.setCantidad(dcc.getCantidad() + cantidad);
					dcc.setValor(dcc.getValor()
							+ ((p.getPrecio() * cantidad) / (p.getDescuento() > 0 ? (p.getDescuento() / 100) : 1)));
				} else {
					dcc = new DetalleCompraCarrito();
					dcc.setCantidad(cantidad);
					dcc.setIdCarritoDeCompras(cc);
					dcc.setProducto(p);
					dcc.setValor((p.getPrecio() * cantidad) / (p.getDescuento() > 0 ? (p.getDescuento() / 100) : 1));

				}
				dccr.save(dcc);
			} else {

				cc = new CarritoDeCompras();
				cc.setUsuarioId(identificacion);
				cc.setFechaCompra(new Timestamp(System.currentTimeMillis()));
				ccr.save(cc);
				DetalleCompraCarrito dcc = new DetalleCompraCarrito();
				Producto p = pr.findById(producto).get();
				dcc.setCantidad(cantidad);
				dcc.setIdCarritoDeCompras(cc);
				dcc.setProducto(p);
				dcc.setValor((p.getPrecio() * cantidad) / (p.getDescuento() > 0 ? (p.getDescuento() / 100) : 1));
				dccr.save(dcc);
			}
			confirmacion = "Producto agregado a carrito de compras";
		} else {
			confirmacion = "No se puede agregar producto a carrito de compras";
		}

		return confirmacion;
	}

	public List<DetalleCompraCarrito> consultarCarritoPorUsuario(Integer identificacion) {
		return dccr.findByIdCarritoDeCompras(ccr.findByUsuarioId(identificacion));
	}

	@Transactional
	public String vaciarCarrito(Integer identificacion, boolean compra) {
		String confirmacion = "";
		try {
			if (!compra) {
				dccr.findByIdCarritoDeCompras(ccr.findByUsuarioId(identificacion))
						.forEach((final DetalleCompraCarrito dcc) -> {
							Producto p = dcc.getProducto();
							p.setCantidad(p.getCantidad() + dcc.getCantidad());
							pr.save(p);
						});
			}
			dccr.deleteByIdCarritoDeCompras(ccr.findByUsuarioId(identificacion));
			ccr.deleteByUsuarioId(identificacion);
			confirmacion = "Carrito de compras eliminado";
		} catch (Exception e) {
			BLog.getLogger().error(e);
			confirmacion = "Error en la eliminacion del carrito de compras";
		}

		return confirmacion;
	}

	@Transactional
	public String confirmarCompra(Integer identificacion) {
		return vaciarCarrito(identificacion, true);
	}

	private boolean agregarProductoCarrito(Integer idProducto, Integer cantidad) {
		Optional<Producto> p = pr.findById(idProducto);
		if (p.isPresent() && p.get().getCantidad() > 0 && ((p.get().getCantidad() - cantidad) >= 0)) {
			p.get().setCantidad(p.get().getCantidad() - cantidad);
			pr.save(p.get());
			return true;
		}
		return false;
	}

}
