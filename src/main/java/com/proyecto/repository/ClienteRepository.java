package com.proyecto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
		
	@Query("select c from Cliente c where (?1 is '' or c.nombres like ?1) and (?2 is '' or c.apellidos like ?2) and"
			+ "(?3 is '' or c.dni like ?3) and (?4 is '' or c.correo like ?4) and"
			+ "(?5 is '' or c.direccion like ?5)  and (?6 is -1 or c.ubigeo.idUbigeo = ?6) and c.estado = ?7 ")       
	public List<Cliente> listarClientes(
			
			String nombres, String apellidos,String dni, String correo,
			String direccion, int idUbigeo, int estado);

}
