package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.proyecto.util.Constantes;
import com.proyecto.entidad.Producto;
import com.proyecto.entidad.Reclamo;
import com.proyecto.entidad.Sede;
import com.proyecto.service.ProductoService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/producto")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProductoController {

	
	
	@Autowired
	private ProductoService productoservice;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Producto>> listaModalidad(){
		List<Producto> lista = productoservice.listarproductos();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public  ResponseEntity<Map<String, Object>> insertaProducto(@RequestBody Producto obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Producto objSalida = productoservice.insertarProducto(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se registró, consulte con el administrador.");
			}else {
				salida.put("mensaje", "Se registró correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	@GetMapping("/listaproductoporparmetros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaproductoporparmetros(
			@RequestParam(value="nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(value="serie", required = false, defaultValue = "") String serie,
			@RequestParam(value="idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(value="idMarca", required = false, defaultValue = "-1") int idMarca,
			@RequestParam(value="estado", required = false, defaultValue = "1") int estado){
		Map<String, Object> salida = new HashMap<String, Object>();
		
		try {
			List<Producto> lista = productoservice.listaproductoporparmetros("%"+nombre+"%","%"+serie+"%",
					idMarca,idPais,estado);
			if(CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existe elementos para la consulta");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Se tiene " + lista.size() + " elementos");
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error : " + e.getMessage());
		}
		
		
		return ResponseEntity.ok(salida);
	}
	
	
	/*****************************************************************************************************************/
	
	
	@GetMapping("/listarproductopornombre/{nombre}")
	@ResponseBody
	public ResponseEntity<List<Producto>> listaProductopornombre(@PathVariable("nombre") String nombre) {
		List<Producto> lista  = null;
		try {
			if (nombre.equals("todos")) {
				lista = productoservice.listaProductopornombre("%");
			}else {
				lista = productoservice.listaProductopornombre("%" + nombre + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/registraProducto")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insetarProducto(@RequestBody Producto obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdProducto(0);
			Producto objSalida =  productoservice.insetaractualizarproducto(obj);
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

	@PutMapping("/actualizaProducto")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaProducto(@RequestBody Producto obj) {
		Map<String, Object> salida = new HashMap<>();
		obj.setEstado(1);
		obj.setFechaRegistro(new Date());
		try {
			Producto objSalida =  productoservice.insetaractualizarproducto(obj);
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
	
	
	@DeleteMapping("/eliminarProducto/{idProducto}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminarProducto(@PathVariable("idProducto") int cod){
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			
			Producto obj = productoservice.findByIdProducto(cod);
			
			obj.setEstado(0);
			obj.setFechaRegistro(new Date());
			Producto objSalida = productoservice.insetaractualizarproducto(obj);
			if(objSalida == null) {
				salida.put("mensaje", "Ocurrio un error, no se elimino");
			}else {
				salida.put("mensaje", "Se elimino correctamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Opps! Algo salio mal, no se elimino. Consulte con soporte");
		}
		
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	
	
}
