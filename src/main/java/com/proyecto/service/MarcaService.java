package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Marca;
import com.proyecto.entidad.Producto;

public interface MarcaService {


	public Marca insertaActualizaMarca(Marca obj);
	
	public List<Marca> listaMarca();
	
	public abstract List<Marca> listaMarcaPorPais(String nombre, String descripcion,String certificado, int idPais, int estado);
	
	public Marca insertaractualizarmarca(Marca marca);
	
    public abstract List<Marca> listaMarcaPorNombreLike(String nombre);
    
    public abstract Marca findByIdMarca(int cod);
    
	

	

}
