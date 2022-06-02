package com.proyecto.controller;

import java.text.SimpleDateFormat;
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


import com.proyecto.entidad.Reclamo;
import com.proyecto.service.ReclamoService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/reclamo")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ReclamoController {

	@Autowired
	private ReclamoService serviceReclamo;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Reclamo>> listarTodosLosReclamos(){
		List<Reclamo> lista = serviceReclamo.listarTodosReclamo();
		return ResponseEntity.ok(lista);
	}
	
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertarReclamo(@RequestBody Reclamo obj){
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			obj.setEstado(1);
			obj.setFechaRegistro(new Date());
			Reclamo objSalida = serviceReclamo.insertarReclamo(obj);
			if(objSalida == null) {
				salida.put("mensaje", "Ocurrio un error, no se registro");
			}else {
				salida.put("mensaje", "Registro exitoso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Opps! Algo salio mal, no se registro. Consulte con soporte");
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listarReclamosPorParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listarReclamosParametros(
			@RequestParam(name = "estado", required = false, defaultValue = "1") int estado,
			@RequestParam(name = "fechaCompra", required = false, defaultValue = "") String fecha,
			@RequestParam(name = "idCliente", required = false, defaultValue = "-1") int idCliente,
			@RequestParam(name = "idTipo", required = false, defaultValue = "-1") int idTipo
			){
		
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date d;
			if(fecha.isEmpty()) {
				d = null;
			}else {
				d = formato.parse(fecha);;
			}
			
			List<Reclamo> lista = serviceReclamo.listarReclamoPorParametros(estado, d, idCliente, idTipo);				
			if(CollectionUtils.isEmpty(lista)){
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
