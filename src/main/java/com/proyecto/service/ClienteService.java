package com.proyecto.service;

import java.util.Date;
import java.util.List;

import com.proyecto.entidad.Cliente;

public interface ClienteService{

	public Cliente insertaCliente(Cliente obj);
	public List<Cliente> listaCliente();
	
		//consulta
		public abstract List<Cliente> listarClientes(
			
			String nombres, String apellidos,String dni, String correo,
			String direccion, int idUbigeo, int estado);

		//crud
		
		  public abstract Cliente insertaActualizaCliente(Cliente obj);
		  public abstract List<Cliente> listaClientePorNombre(String nombres);
		

}
