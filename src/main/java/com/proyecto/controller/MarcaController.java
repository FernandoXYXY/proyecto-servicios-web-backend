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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
