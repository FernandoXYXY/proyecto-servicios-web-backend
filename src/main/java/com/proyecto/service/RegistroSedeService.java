package com.proyecto.service;

import java.util.List;

 

import com.proyecto.entidad.Sede;

public interface RegistroSedeService {
	
	public Sede insertaSede(Sede obj);
	public List<Sede> listaSedes();
	public List<Sede> listaSedePorPaisNombreEstado(
			String nombre, int estado, int pais, String codigoPostal, int idSede);
	
}
