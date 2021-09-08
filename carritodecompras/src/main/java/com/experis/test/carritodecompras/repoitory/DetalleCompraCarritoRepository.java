package com.experis.test.carritodecompras.repoitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.experis.test.carritodecompras.entity.CarritoDeCompras;
import com.experis.test.carritodecompras.entity.DetalleCompraCarrito;
import com.experis.test.carritodecompras.entity.Producto;

public interface DetalleCompraCarritoRepository extends JpaRepository<DetalleCompraCarrito, Integer> {

	public List<DetalleCompraCarrito> findByIdCarritoDeCompras(CarritoDeCompras idCarritoDeCompras);

	public List<DetalleCompraCarrito> findByProducto(Producto producto);

	public DetalleCompraCarrito findByIdCarritoDeComprasAndProducto(CarritoDeCompras idCarritoDeCompras,
			Producto producto);

	
	public void deleteByIdCarritoDeCompras(CarritoDeCompras idCarritoDeCompras);
	

}
