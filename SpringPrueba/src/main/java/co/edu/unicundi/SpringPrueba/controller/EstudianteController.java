package co.edu.unicundi.SpringPrueba.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import co.edu.unicundi.SpringPrueba.entity.Estudiante;
import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.exception.RegisteredObjectException;
import co.edu.unicundi.SpringPrueba.service.IEstudianteService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/estudiantes")
@Api(value = "Users microservice", description = "Gestion de los estudiantes")
public class EstudianteController {

	@Autowired
	private IEstudianteService estudianteService;

	@PostMapping(path = "/crear")
	@ApiOperation(value = "Crear estudiante", notes = "Crear un estudiante en la lista")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Creado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "La cedula ya existe"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Campo cedula requerido") })
	public ResponseEntity<?> crear(
			@ApiParam(name = "Estudiante", type = "Object", value = "Objeto estudiante con sus datos", required = true) @Valid @RequestBody Estudiante estudiante)
			throws RegisteredObjectException, FieldRequiredException, ObjectNotFoundException {

		estudianteService.crear(estudiante);
		return new ResponseEntity<String>("Estudiante registrado correctamente", HttpStatus.CREATED);
	}

	@PutMapping(path = "/editar")
	@ApiOperation(value = "Editar estudiante", notes = "Editar a un estudiante de la lista si existe")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "Editado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "La cedula ya existe"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "El id del estudiante no existe"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Campo cedula y id requeridos") })
	public ResponseEntity<?> editar(
			@ApiParam(name = "Estudiante", type = "Object", value = "Objeto estudiante con los datos a editar", required = true) @Valid @RequestBody Estudiante estudiante)
			throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException {

		estudianteService.editar(estudiante);
		return new ResponseEntity<String>("Estudiante editado correctamente", HttpStatus.OK);
	}

	@DeleteMapping(path = "/eliminar/{id}")
	@ApiOperation(value = "Eliminar estudiante", notes = "Eliminar a un estudiante de la lista si existe")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Eliminado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "El id del estudiante no existe") })
	public ResponseEntity<?> eliminar(
			@ApiParam(name = "id", type = "Integer", value = "Id del estudiante a eliminar", required = true) @PathVariable Integer id)
			throws ObjectNotFoundException {

		estudianteService.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/listar/{nPagina}/{cantidad}")
	@ApiOperation(value = "Listar estudiantes", notes = "Lista todos los estudiantes según el paginado", response = Estudiante.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "La lista trae uno o más estudiantes"),
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "La lista está vacia") })
	public ResponseEntity<?> listar(
			@ApiParam(name = "numero página", type = "Integer", value = "Número de la página a listar", required = true) @PathVariable Integer nPagina,
			@ApiParam(name = "tamaño página", type = "Integer", value = "Cantitdad de datos dentro de la página a listar", required = true) @PathVariable Integer cantidad
			) throws ListNoContentException, ParameterInvalidException {

		Page<Estudiante> estudiantes = estudianteService.listar(nPagina, cantidad);
		return new ResponseEntity<Page<Estudiante>>(estudiantes, HttpStatus.OK);
	}

	@GetMapping(path = "/obtenerPorId/{id}")
	@ApiOperation(value = "Obtener por id", notes = "Obtiene al estudiante filtrando por id")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "Estudiante encontrado"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se encontro el estudiante") })
	public ResponseEntity<?> obtenerPorId(
			@ApiParam(name = "id", type = "Integer", value = "Id del estudiante a obtener", required = true) @PathVariable Integer id)
			throws ObjectNotFoundException {

		Estudiante estudiante = estudianteService.obtenerPorId(id);
		return new ResponseEntity<Estudiante>(estudiante, HttpStatus.OK);
	}
}

