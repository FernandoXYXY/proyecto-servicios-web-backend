package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Proveedor;
import com.proyecto.repository.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService{

	@Autowired
	private ProveedorRepository reposi;

	@Override
	public List<Proveedor> listaProveedor() {
		return reposi.findAll();
	}
	@Override
	public Proveedor insertaProveedor(Proveedor obj) {
		return reposi.save(obj);
	}
	@Override
	public List<Proveedor> listaProveedorPorTodosUbigeo(String razonsocial, String ruc, String direccion,
			String telefono, String celular, String contacto, int idUbigeo, int estado) {
		
		return reposi.listaProveedorPorTodosUbigeo(razonsocial, ruc, direccion, telefono, celular, contacto, idUbigeo, estado);
	}

	
}
