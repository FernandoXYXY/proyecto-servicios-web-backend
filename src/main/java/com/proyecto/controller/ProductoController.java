package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Producto;
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
	
}
