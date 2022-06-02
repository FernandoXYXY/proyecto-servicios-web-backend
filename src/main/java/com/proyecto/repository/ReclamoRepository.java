package com.proyecto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Reclamo;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer>{
	
	@Query("select r from Reclamo r where (estado = ?1) and (?2 is null or r.fechaCompra = ?2) and (?3 is -1 or r.cliente.idCliente = ?3) and (?4 is -1 or r.tipoReclamo.idTipoReclamo = ?4)")
	public List<Reclamo> litarReclamoPorParametros(int estado, Date fCompra, int idCliente, int idTipo);
}
