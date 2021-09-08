package com.experis.test.carritodecompras.repoitory;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.experis.test.carritodecompras.entity.Producto;

public interface ProductoRepository extends PagingAndSortingRepository<Producto, Integer> {

	public List<Producto> findByNombreContaining(String nombre, Pageable pageable);
	
	public Producto findByNombre(String nombre);

	public List<Producto> findByPrecioBetween(Integer valor1, Integer valor2, Pageable pageable);

	public List<Producto> findByMarca(String marca, Pageable pageable);

}
