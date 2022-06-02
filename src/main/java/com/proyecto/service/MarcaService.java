package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Marca;

public interface MarcaService {

	//Para el Crud
	public Marca insertaActualizaMarca(Marca obj);
	public Marca insertarActualizaMarca(Marca obj);
	public abstract void eliminaPorId(int idMarca);
	public abstract Optional<Marca> listaMarcaPorId(int idMarca);
	public List<Marca> listaMarca();
    public abstract List<Marca> listaMarcaPorNombreLike(String nombre);
	
	//Para la consulta
	public abstract List<Marca> listaMarcaPorPais(
			String nombre, String descripcion,String certificado, int idPais, int estado);

}
