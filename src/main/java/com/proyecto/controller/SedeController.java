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

import com.proyecto.entidad.Sede;
import com.proyecto.service.RegistroSedeService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/sede")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class SedeController {
	@Autowired
	private RegistroSedeService registroSedeService;
	
	@GetMapping
	@RequestMapping("/listaSedes")
	@ResponseBody
	public ResponseEntity<List<Sede>> ListaSede(){
		List<Sede> lista = registroSedeService.listaSedes();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@RequestMapping("/RegistraSede")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> RegistraSede(@RequestBody Sede obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setEstado(1);
			obj.setFechaRegistro(new Date());
			Sede objSede = registroSedeService.insertaSede(obj);;
			if(objSede.getNombre() == null) {
				salida.put("mensaje", "no se registro la sede");
			}else {
				salida.put("mensaje", "Se registro Correctamente la Sede");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			salida.put("mensaje", "No se registro, Felicidades tu trabajo esta mal hecho");
		}
		
		return ResponseEntity.ok(salida);
		
	}
	
	@GetMapping("/listaSedeNEP")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaSedeNEP(
			@RequestParam(value="nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(value="estado", required = false, defaultValue = "-1") int estado,
			@RequestParam(value="pais", required = false, defaultValue = "-1") int pais,
			@RequestParam(value="codPostal", required = false, defaultValue = "") String codPostal,
			@RequestParam(value="idSede", required = false, defaultValue = "-1") int idSede ){
		Map<String, Object> salida = new HashMap<String, Object>();
		
		try {
			List<Sede> lista = registroSedeService.listaSedePorPaisNombreEstado(nombre + "%",
					estado, pais,"%" + codPostal + "%", idSede);
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
