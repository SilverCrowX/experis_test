package com.experis.test.carritodecompras.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.experis.test.carritodecompras.entity.Producto;
import com.experis.test.carritodecompras.repoitory.ProductoRepository;
import com.experis.test.carritodecompras.utilidades.BLog;

@Service
public class ArchivoService {

	@Autowired
	ProductoRepository pr;

	public Boolean cargarArchivoCSV(MultipartFile archivo) {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(archivo.getInputStream()));
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] producto = linea.split(",");
				if (producto.length == 6 && !producto[0].equalsIgnoreCase("nombre") && validarregistro(producto)) {

					Producto p = pr.findByNombre(producto[0]);
					if (p != null) {
						p.setPrecio(Integer.parseInt(producto[2]));
						p.setCantidad(Integer.parseInt(producto[3]));
						p.setEstado(producto[4]);
						p.setDescuento(Double.parseDouble(producto[5]));
					} else {
						p = new Producto();
						p.setNombre(producto[0]);
						p.setMarca(producto[1]);
						p.setPrecio(Integer.parseInt(producto[2]));
						p.setCantidad(Integer.parseInt(producto[3]));
						p.setEstado(producto[4]);
						p.setDescuento(Double.parseDouble(producto[5]));
					}
					pr.save(p);
				} else {
					BLog.getLogger().error("No se puede cargar archivo error en registro [ " + linea + " ] ");
				}
			}
			return true;
		} catch (Exception e) {
			BLog.getLogger().error(e);
		}

		return false;
	}

	private boolean validarregistro(String[] producto) {
		for (String p : producto) {

			if (p != null && p.equalsIgnoreCase(""))
				return false;
		}
		return true;
	}

}
