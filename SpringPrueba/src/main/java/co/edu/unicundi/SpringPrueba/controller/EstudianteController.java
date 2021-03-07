package co.edu.unicundi.SpringPrueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicundi.SpringPrueba.dto.Estudiante;
import co.edu.unicundi.SpringPrueba.service.IEstudianteService;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
	
	@Autowired
	private IEstudianteService estudianteService;
	
	// @RequestMapping(value = "/obtener", method = RequestMethod.GET) funciona pero por buenas practias es mejor @GetMapping
	@GetMapping(path = "/obtener/{id}")
	public Estudiante obtener(@PathVariable Integer id) {
		return estudianteService.obtener();
	}
	
	@PostMapping(path = "/crear")
	public void crear(@RequestBody Estudiante estudiante) {
		
	}
}
