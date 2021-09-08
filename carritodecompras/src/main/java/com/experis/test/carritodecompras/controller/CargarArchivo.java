package com.experis.test.carritodecompras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.experis.test.carritodecompras.service.ArchivoService;

@RestController
@RequestMapping("/experis")
public class CargarArchivo {
	@Autowired
	ArchivoService as;

	@CrossOrigin
	@PostMapping("/cargarArchivo")
	public String cargar(@RequestBody MultipartFile archivo) {
		return as.cargarArchivoCSV(archivo) ? "Ok" : "Error";
	}

}
