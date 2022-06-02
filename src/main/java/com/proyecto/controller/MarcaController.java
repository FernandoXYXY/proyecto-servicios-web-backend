package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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


import com.proyecto.entidad.Marca;
import com.proyecto.entidad.Proveedor;
import com.proyecto.service.MarcaService;
import com.proyecto.util.AppSettings;
import com.proyecto.util.Constantes;




@RestController
@RequestMapping("/url/marca")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class MarcaController {
	
	@Autowired
	private MarcaService marcaService;

	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List< Marca>> listaMarca(){
		List< Marca> lista = marcaService.listaMarca();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public  ResponseEntity<Map<String, Object>> insertaMarca(@RequestBody  Marca obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			 Marca objSalida = marcaService.insertaActualizaMarca(obj);
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
	
	
	@GetMapping("/listaMarcaConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaMarcaPorPais(
			@RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(value = "descripcion", required = false, defaultValue = "") String descripcion,
			@RequestParam(value = "certificado", required = false, defaultValue = "") String certificado,
			@RequestParam(value = "idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(value = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<String, Object>();
		
		try {
			List<Marca> lista = marcaService.listaMarcaPorPais(
					
					nombre + "%", "%"+descripcion+"%", "%"+certificado+"%", idPais, estado);
			
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
	

	@PostMapping("/registraMarca")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertarMarca(@RequestBody Marca obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdMarca(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Marca objSalida =  marcaService.insertaActualizaMarca(obj);
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

	@PutMapping("/actualizaMarca")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaMarca(@RequestBody Marca obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Marca objSalida =  marcaService.insertaActualizaMarca(obj);
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
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> eliminaAlumno(@PathVariable int id) {
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			Optional<Marca> optional =  marcaService.listaMarcaPorId(id);
			if (optional.isPresent()) {
				marcaService.eliminaPorId(id);
				salida.put("mensaje", "Eliminación exitosa");
			}else {
				salida.put("mensaje", "El ID no existe : " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la eliminación " + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	
	
	
	
	
	
}
