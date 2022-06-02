package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Producto;

public interface ProductoService {

	public abstract Producto insertarProducto(Producto obj);
	
	public  List<Producto> listarproductos();
	
	public List<Producto> listaproductoporparmetros(String nombre, String serie, int idMarca, int idPais, int estado );
	
	
	
	
	

}
