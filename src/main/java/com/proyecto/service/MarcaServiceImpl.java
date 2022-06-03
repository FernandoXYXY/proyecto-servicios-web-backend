package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Marca;
import com.proyecto.entidad.Producto;
import com.proyecto.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaRepository Repository;

	
	@Override
	public Marca insertaActualizaMarca(Marca obj) {
		
		return Repository.save(obj);
	}
	
	@Override
	public List<Marca> listaMarca() {
		return Repository.findAll();
	}

	
	@Override
	public List<Marca> listaMarcaPorPais(String nombre, String descripcion, String certificado, int idPais,
			int estado) {
		
		
		return Repository.listaMarcaPorPais(nombre,descripcion,certificado,idPais,estado);
	}

	

	

	@Override
	public List<Marca> listaMarcaPorNombreLike(String nombre) {
		return Repository.listaPorNombreLike(nombre);
	}

	@Override
	public Marca findByIdMarca(int cod) {
		return Repository.findByIdMarca(cod);
	}

	@Override
	public Marca insertaractualizarmarca(Marca marca) {
		return Repository.save(marca);
	}

	

}
