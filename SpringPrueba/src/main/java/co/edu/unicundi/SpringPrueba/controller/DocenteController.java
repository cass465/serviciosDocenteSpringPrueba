package co.edu.unicundi.SpringPrueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicundi.SpringPrueba.dto.Docente;
import co.edu.unicundi.SpringPrueba.service.IDocenteService;

@RestController
@RequestMapping("/docentes")
public class DocenteController {

	@Autowired
	private IDocenteService docenteService;

	@PostMapping(path = "/crear")
	public ResponseEntity<?> crear(@RequestBody Docente docente) {
		String respuesta = docenteService.crear(docente);
		HttpStatus status = null;

		switch (respuesta) {
		case "Docente registrado correctamente":
			status = HttpStatus.CREATED;
			break;
		case "La cedula ingresada ya existe":
			status = HttpStatus.BAD_REQUEST;
			break;
		case "Campo cedula requerido":
			status = HttpStatus.BAD_REQUEST;
			break;
		}

		return new ResponseEntity<String>(respuesta, status);
	}

	@PutMapping(path = "/editar")
	public ResponseEntity<?> editar(@RequestBody Docente docente) {
		String respuesta = docenteService.editar(docente);
		HttpStatus status = null;

		switch (respuesta) {
		case "Docente editado correctamente":
			status = HttpStatus.OK;
			break;
		case "La cedula ingresada ya existe":
			status = HttpStatus.BAD_REQUEST;
			break;
		case "El id del docente no existe":
			status = HttpStatus.NOT_FOUND;
			break;
		case "Campo cedula requerido":
			status = HttpStatus.BAD_REQUEST;
			break;
		case "Campo id requerido":
			status = HttpStatus.BAD_REQUEST;
			break;
		}

		return new ResponseEntity<String>(respuesta, status);
	}

	@DeleteMapping(path = "/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id) {
		String respuesta = docenteService.eliminar(id);
		HttpStatus status = null;

		switch (respuesta) {
		case "Docente eliminado correctamente":
			status = HttpStatus.NO_CONTENT;
			break;
		case "El id del docente no existe":
			status = HttpStatus.NOT_FOUND;
			break;
		}

		return new ResponseEntity<String>(respuesta, status);
	}

	@GetMapping(path = "/listar")
	public ResponseEntity<?> listar() {
		List<Docente> docentes = docenteService.listar();

		if (docentes.size() > 0) {
			return new ResponseEntity<List<Docente>>(docentes, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(path = "/obtener/{id}")
	public ResponseEntity<?> obtener(@PathVariable Integer id) {
		Docente docente = docenteService.obtenerPorId(id);

		if (docente.getId() != null) {
			return new ResponseEntity<Docente>(docente, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Objeto docente no encontrado", HttpStatus.NOT_FOUND);
		}
	}
}
