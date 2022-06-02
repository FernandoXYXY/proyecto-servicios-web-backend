package com.proyecto.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Reclamo;
import com.proyecto.repository.ReclamoRepository;

@Service
public class ReclamoServiceImpl implements ReclamoService{

	@Autowired
	private ReclamoRepository repository;
	
	@Override
	public Reclamo insertarReclamo(Reclamo reclamo) {
		return repository.save(reclamo);
	}

	@Override
	public List<Reclamo> listarTodosReclamo() {
		return repository.findAll();
	}

	@Override
	public List<Reclamo> listarReclamoPorParametros(int estado, Date fecha, int idCliente, int idTipo) {
		return repository.litarReclamoPorParametros(estado, fecha, idCliente, idTipo);
	}

	@Override
	public Reclamo actualizarReclamo(Reclamo reclamo) {
		return repository.save(reclamo);
	}

	@Override
	public Reclamo buscarPorCodigo(int cod) {
		return repository.findByIdReclamo(cod);
	}

	@Override
	public List<Reclamo> listar(int estado) {
		return repository.findByEstado(estado);
	}

}
