package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Cliente;
import com.proyecto.service.ClienteService;
import com.proyecto.util.AppSettings;
import com.proyecto.util.Constantes;

@RestController
@RequestMapping("/url/cliente")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ClienteController {
	/*aea manito*/
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping	
	@ResponseBody
	public ResponseEntity<List<Cliente>> ListaCliente(){
		List<Cliente> lista = clienteService.listaCliente();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> RegistraCliente(@RequestBody Cliente obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Cliente objSalida = clienteService.insertaCliente(obj);
			if(objSalida == null) {
				salida.put("mensaje", "No se registro el cliente");
			}else {
				salida.put("mensaje", "Se registro el cliente correctamente");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			salida.put("mensaje", "No se registró, consulte con el administrador");
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listaClienteConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaProveedorPorTodosConUbigeo(
			@RequestParam(name = "nombres", required = false, defaultValue = "") String nombres,
			@RequestParam(name = "apellidos", required = false, defaultValue = "") String apellidos,
			@RequestParam(name = "dni", required = false, defaultValue = "") String dni,
			@RequestParam(name = "correo", required = false, defaultValue = "") String correo,
			@RequestParam(name = "direccion", required = false, defaultValue = "") String direccion,
			@RequestParam(name = "idUbigeo", required = false, defaultValue = "-1") int idUbigeo,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Cliente> lista = clienteService.listarClientes(nombres+"%", apellidos+"%",dni+"%","%"+correo+"%", "%"+direccion+"%",idUbigeo, estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	/*crud*/
	
	@GetMapping("/listarclientepornombre/{filtro}")
	@ResponseBody
	public ResponseEntity<List<Cliente>> listaClientePorNombre(@PathVariable("filtro") String filtro) {
		List<Cliente> lista  = null;
		try {
			if (filtro.equals("todos")) {
				lista = clienteService.listaClientePorNombre("%");
			}else {
				lista = clienteService.listaClientePorNombre("%" + filtro + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/registraCliente")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaActualizaCliente(@RequestBody Cliente obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdCliente(0);
			Cliente objSalida =  clienteService.insertaActualizaCliente(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	@PutMapping("/actualizaCliente")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaDocente(@RequestBody Cliente obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Cliente objSalida =  clienteService.insertaActualizaCliente(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_ACT_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
}