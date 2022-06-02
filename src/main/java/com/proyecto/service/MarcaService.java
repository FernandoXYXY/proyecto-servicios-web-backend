package com.proyecto.service;

import java.util.List;


import com.proyecto.entidad.Marca;

public interface MarcaService {

	
	public Marca insertaActualizaMarca(Marca obj);
	public List<Marca> listaMarca();
	
	public abstract List<Marca> listaMarcaPorPais(
			String nombre, String descripcion,String certificado, int idPais, int estado);

}
