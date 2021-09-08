package com.experis.test.carritodecompras.repoitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.experis.test.carritodecompras.entity.CarritoDeCompras;

public interface CarritoDeComprasRepository extends JpaRepository<CarritoDeCompras, Integer> {

	public CarritoDeCompras findByUsuarioId(Integer usuarioId);

	public void deleteByUsuarioId(Integer usuarioId);

}
