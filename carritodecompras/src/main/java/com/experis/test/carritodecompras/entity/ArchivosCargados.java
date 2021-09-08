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
@Data
@Table(name = "ARCHIVOS_CARGADOS")
public class ArchivosCargados {

	@Id
	@Column(name = "ARCHIVO_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer archivoId;

	@Column(name = "NOMBRE_ARCHIVO", nullable = false, length = 50)
	private String nombreArchivo;

	@Column(name = "FECHA_CARGA")
	private Timestamp fechaCarga;

	@Column(name = "OBSERVACIONES", nullable = false)
	private String observaciones;
}
