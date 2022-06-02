package com.proyecto.service;

import java.util.Date;
import java.util.List;

import com.proyecto.entidad.Reclamo;

public interface ReclamoService {
	
	public abstract List<Reclamo> listarTodosReclamo();
	public abstract Reclamo insertarReclamo(Reclamo reclamo);
	public abstract Reclamo actualizarReclamo(Reclamo reclamo);
	public abstract Reclamo buscarPorCodigo(int cod);
	public abstract List<Reclamo> listarReclamoPorParametros(int estado, Date fecha, int idCliente, int idTipo);

}
