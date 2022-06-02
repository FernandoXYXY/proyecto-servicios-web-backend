package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Producto;



public interface ProductoRespository extends JpaRepository<Producto, Integer>{
	
	@Query("select p from Producto p where ( ?1 is '' or p.nombre like ?1 ) and  ( ?2 is '' or p.serie like ?2 ) and ( ?3 is -1 or p.marca.idMarca = ?3) "
			+ "and ( ?4 is -1 or p.pais.idPais = ?4) and p.estado = ?5")
	public  List<Producto> listaproductoporparmetros(String nombre, String serie, int idMarca, int idPais, int estado );
 
		
	
}
