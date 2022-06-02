package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.proyecto.entidad.Producto;
import com.proyecto.repository.ProductoRespository;

@Service
public class ProductoServiceImpl implements ProductoService {

	
	@Autowired
	private ProductoRespository repository;
	
	@Override
	public Producto insertarProducto(Producto obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}

	@Override
	public List<Producto> listarproductos() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Producto> listaproductoporparmetros(String nombre, String serie, int idMarca, int idPais, int estado) {
		// TODO Auto-generated method stub
		return repository.listaproductoporparmetros(nombre, serie, idMarca, idPais, estado);
	}

	

}
