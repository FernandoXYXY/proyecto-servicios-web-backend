package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{

	
	@Query("select x from Proveedor x where (?1 is '' or x.razonsocial like ?1) and (?2 is '' or x.ruc like ?2) and"
			+ "(?3 is '' or x.direccion like ?3) and (?4 is '' or x.telefono like ?4) and (?5 is '' or x.celular like ?5) and"
			+ "(?6 is '' or x.contacto like ?6) and (?7 is -1 or x.ubigeo.idUbigeo = ?7) and x.estado = ?8 ")       
	public List<Proveedor> listaProveedorPorTodosUbigeo(
			
			String razonsocial, String ruc,String direccion,String telefono,
			String celular,String contacto, int idUbigeo, int estado);
	
}
