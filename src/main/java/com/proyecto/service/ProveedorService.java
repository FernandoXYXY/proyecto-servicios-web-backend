package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Proveedor;

public interface ProveedorService {

	public abstract List<Proveedor> listaProveedor();
	public abstract Proveedor insertaProveedor(Proveedor obj);
	
	
	
	public abstract List<Proveedor> listaProveedorPorTodosUbigeo(
			String razonsocial, String ruc,String direccion,String telefono,
			String celular, String contacto, int idUbigeo, int estado);
}
