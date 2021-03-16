package co.edu.unicundi.SpringPrueba.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/docentes")
@Api(value = "Users microservice", description = "Gestion de los docentes")
public class DocenteController {

	@Autowired
	private IDocenteService docenteService;

	@PostMapping(path = "/crear")

	@ApiOperation(value = "Crear docente", notes = "Crear un docente en la lista")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Creado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "La cedula ya existe"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Campo cedula requerido") })

	public ResponseEntity<?> crear(
			@ApiParam(name = "Docente", type = "Object", value = "Objeto docente con sus datos", required = true) @RequestBody Docente docente) {
		String respuesta = docenteService.crear(docente);
		HttpStatus status = null;

		switch (respuesta) {
		case "Docente registrado correctamente":
			status = HttpStatus.CREATED;
			break;
		case "La cedula ingresada ya existe":
			status = HttpStatus.CONFLICT;
			break;
		case "Campo cedula requerido":
			status = HttpStatus.BAD_REQUEST;
			break;
		}

		return new ResponseEntity<String>(respuesta, status);
	}

	@PutMapping(path = "/editar")
	@ApiOperation(value = "Editar docente", notes = "Editar a un docente de la lista si existe")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "Editado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "La cedula ya existe"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "El id del docente no existe"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Campo cedula y id requeridos") })

	public ResponseEntity<?> editar(
			@ApiParam(name = "Docente", type = "Object", value = "Objeto docente con los datos a editar", required = true) @RequestBody Docente docente) {
		String respuesta = docenteService.editar(docente);
		HttpStatus status = null;

		switch (respuesta) {
		case "Docente editado correctamente":
			status = HttpStatus.OK;
			break;
		case "La cedula ingresada ya existe":
			status = HttpStatus.CONFLICT;
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
	@ApiOperation(value = "Eliminar docente", notes = "Eliminar a un docente de la lista si existe")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Eliminado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "El id del docente no existe") })
	public ResponseEntity<?> eliminar(@ApiParam(name = "id", type = "Integer", value = "Id del docente a eliminar", required = true) @PathVariable Integer id) {
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
	@ApiOperation(value = "Listar docentes", notes = "Lista todos los docentes", response=Docente.class)
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "La lista trae uno o más docentes"),
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "La lista está vacia") })
	public ResponseEntity<?> listar() {
		List<Docente> docentes = docenteService.listar();

		if (docentes.size() > 0) {
			return new ResponseEntity<List<Docente>>(docentes, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(path = "/obtener/{id}")
	@ApiOperation(value = "Obtener por id", notes = "Obtiene al docente filtrando por id")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "Docente encontrado"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se encontro el docente") })
	public ResponseEntity<?> obtener(@ApiParam(name = "id", type = "Integer", value = "Id del docente a obtener", required = true) @PathVariable Integer id) {
		Docente docente = docenteService.obtenerPorId(id);

		if (docente.getId() != null) {
			return new ResponseEntity<Docente>(docente, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Objeto docente no encontrado", HttpStatus.NOT_FOUND);
		}
	}
}
