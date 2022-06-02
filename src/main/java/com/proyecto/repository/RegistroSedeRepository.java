package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.entidad.Sede;

public interface RegistroSedeRepository extends JpaRepository<Sede, Integer>{
	
	@Query("select s from Sede s where "
			+ "( ?1 is '' or s.nombre like ?1 ) and  "
			+ "( ?2 is -1 or s.estado = ?2 ) and "
			+ "( ?3 is -1 or s.pais.idPais = ?3) and "
			+ "( ?4 is '' or s.codigoPostal like ?4) and"
			+ "( ?5 is -1 or s.idSede = ?5) " )
	public abstract List<Sede> listaSedePorPaisNombreEstado(String nombre, int estado, int pais, String codigoPostal, int idSede);
 
	
	
 
}
