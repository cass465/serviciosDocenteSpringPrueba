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

import co.edu.unicundi.SpringPrueba.dto.DocenteGruposDTO;
import co.edu.unicundi.SpringPrueba.entity.Docente;
import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.exception.RegisteredObjectException;
import co.edu.unicundi.SpringPrueba.service.IDocenteService;
import co.edu.unicundi.SpringPrueba.service.IGrupoDocenteService;
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
	
	@Autowired
	private IGrupoDocenteService grupoDocenteService;

	@PostMapping(path = "/crear")
	@ApiOperation(value = "Crear docente", notes = "Crear un docente en la lista")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Creado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "La cedula ya existe"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Campo cedula requerido") })
	public ResponseEntity<?> crear(
			@ApiParam(name = "Docente", type = "Object", value = "Objeto docente con sus datos", required = true) @Valid @RequestBody Docente docente)
			throws RegisteredObjectException, FieldRequiredException, ObjectNotFoundException {

		docenteService.crear(docente);
		return new ResponseEntity<String>("Docente registrado correctamente", HttpStatus.CREATED);
	}

	@PutMapping(path = "/editar")
	@ApiOperation(value = "Editar docente", notes = "Editar a un docente de la lista si existe")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "Editado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "La cedula ya existe"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "El id del docente no existe"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Campo cedula y id requeridos") })
	public ResponseEntity<?> editar(
			@ApiParam(name = "Docente", type = "Object", value = "Objeto docente con los datos a editar", required = true) @Valid @RequestBody Docente docente)
			throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException {

		docenteService.editar(docente);
		return new ResponseEntity<String>("Docente editado correctamente", HttpStatus.OK);
	}

	@DeleteMapping(path = "/eliminar/{id}")
	@ApiOperation(value = "Eliminar docente", notes = "Eliminar a un docente de la lista si existe")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Eliminado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "El id del docente no existe") })
	public ResponseEntity<?> eliminar(
			@ApiParam(name = "id", type = "Integer", value = "Id del docente a eliminar", required = true) @PathVariable Integer id)
			throws ObjectNotFoundException {

		docenteService.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/listar/{nPagina}/{cantidad}/{orden}")
	@ApiOperation(value = "Listar docentes", notes = "Lista todos los docentes seg??n el paginado", response = Docente.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "La lista trae uno o m??s docentes"),
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "La lista est?? vacia") })
	public ResponseEntity<?> listar(
			@ApiParam(name = "numero p??gina", type = "Integer", value = "N??mero de la p??gina a listar", required = true) @PathVariable Integer nPagina,
			@ApiParam(name = "tama??o p??gina", type = "Integer", value = "Cantitdad de datos dentro de la p??gina a listar", required = true) @PathVariable Integer cantidad,
			@ApiParam(name = "orden de listado", type = "String", value = "Orden del listado (ascendente o descendente)", required = true) @PathVariable String orden)
			throws ListNoContentException, ParameterInvalidException {

		Page<Docente> docentes = docenteService.listar(nPagina, cantidad, orden);
		return new ResponseEntity<Page<Docente>>(docentes, HttpStatus.OK);
	}

	@GetMapping(path = "/obtenerPorId/{id}")
	@ApiOperation(value = "Obtener por id", notes = "Obtiene al docente filtrando por id")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "Docente encontrado"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se encontro el docente") })
	public ResponseEntity<?> obtenerPorId(
			@ApiParam(name = "id", type = "Integer", value = "Id del docente a obtener", required = true) @PathVariable Integer id)
			throws ObjectNotFoundException {

		Docente docente = docenteService.obtenerPorId(id);
		return new ResponseEntity<Docente>(docente, HttpStatus.OK);
	}

	@GetMapping(path = "/obtenerPorCedula/{cedula}")
	@ApiOperation(value = "Obtener por cedula", notes = "Obtiene al docente filtrando por cedula")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "Docente encontrado"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se encontro el docente") })
	public ResponseEntity<?> obtenerPorCedula(
			@ApiParam(name = "cedula", type = "String", value = "Correo del docente a obtener", required = true) @PathVariable String cedula)
			throws ObjectNotFoundException {

		Docente docente = docenteService.obtenerPorCedula(cedula);
		return new ResponseEntity<Docente>(docente, HttpStatus.OK);
	}

	@GetMapping(path = "/listarPorNombreOApellido/{nPagina}/{cantidad}/{nombre}/{apellido}")
	@ApiOperation(value = "Listar docentes por nombre o apellido", notes = "Lista todos los docentes con nombre o apellido seg??n el paginado", response = Docente.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "La lista trae uno o m??s docentes"),
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "La lista est?? vacia") })
	public ResponseEntity<?> listarPorNombreOApellido(
			@ApiParam(name = "numero p??gina", type = "Integer", value = "N??mero de la p??gina a listar", required = true) @PathVariable Integer nPagina,
			@ApiParam(name = "tama??o p??gina", type = "Integer", value = "Cantitdad de datos dentro de la p??gina a listar", required = true) @PathVariable Integer cantidad,
			@ApiParam(name = "nombre", type = "String", value = "Nombre de la consulta", required = true) @PathVariable String nombre,
			@ApiParam(name = "apellido", type = "String", value = "Apellido de la consulta", required = true) @PathVariable String apellido)
			throws ListNoContentException, ParameterInvalidException {

		Page<Docente> docentes = docenteService.listarPorNombreOApellido(nPagina, cantidad, nombre, apellido);
		return new ResponseEntity<Page<Docente>>(docentes, HttpStatus.OK);
	}

	@GetMapping(path = "/listarPorNombreEstudiante/{nPagina}/{cantidad}/{nombre}")
	@ApiOperation(value = "Listar docentes por nombre de estudiante", notes = "Lista todos los docentes por nombre de estudiante seg??n el paginado", response = Docente.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "La lista trae uno o m??s docentes"),
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "La lista est?? vacia") })
	public ResponseEntity<?> listarPorNombreEstudiante(
			@ApiParam(name = "numero p??gina", type = "Integer", value = "N??mero de la p??gina a listar", required = true) @PathVariable Integer nPagina,
			@ApiParam(name = "tama??o p??gina", type = "Integer", value = "Cantitdad de datos dentro de la p??gina a listar", required = true) @PathVariable Integer cantidad,
			@ApiParam(name = "nombre", type = "String", value = "Nombre de la consulta", required = true) @PathVariable String nombre)
			throws ListNoContentException, ParameterInvalidException {

		Page<Docente> docentes = docenteService.listarPorNombreEstudiante(nPagina, cantidad, nombre);
		return new ResponseEntity<Page<Docente>>(docentes, HttpStatus.OK);
	}

	@GetMapping(path = "/listarPorDireccion/{nPagina}/{cantidad}/{criterio}/{valor}")
	@ApiOperation(value = "Listar docentes por direccion", notes = "Lista todos los docentes con direccion seg??n el paginado", response = Docente.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "La lista trae uno o m??s docentes"),
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "La lista est?? vacia") })
	public ResponseEntity<?> listarPorDireccion(
			@ApiParam(name = "numero p??gina", type = "Integer", value = "N??mero de la p??gina a listar", required = true) @PathVariable Integer nPagina,
			@ApiParam(name = "tama??o p??gina", type = "Integer", value = "Cantitdad de datos dentro de la p??gina a listar", required = true) @PathVariable Integer cantidad,
			@ApiParam(name = "criterio", type = "String", value = "Criterio de la consulta (detalle, barrio, ciudad, pais)", required = true) @PathVariable String criterio,
			@ApiParam(name = "valor", type = "String", value = "Valor del filtro de consulta", required = true) @PathVariable String valor)
			throws ListNoContentException, ParameterInvalidException {

		Page<Docente> docentes = docenteService.listarPorDireccion(nPagina, cantidad, criterio, valor);
		return new ResponseEntity<Page<Docente>>(docentes, HttpStatus.OK);
	}
	
	@GetMapping(path = "/obtenerGruposPorIdDocente/{idDocente}")
	@ApiOperation(value = "Obtener docente con sus grupos por id", notes = "Obtiene al docente filtrando por id con sus grupos")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "Docente encontrado"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se encontro el docente") })
	public ResponseEntity<?> obtenerGruposPorIdDocente(
			@ApiParam(name = "id", type = "Integer", value = "Id del docente a obtener", required = true) @PathVariable Integer idDocente)
			throws ObjectNotFoundException {

		DocenteGruposDTO docente = grupoDocenteService.obtenerGruposPorIdDocente(idDocente);
		return new ResponseEntity<DocenteGruposDTO>(docente, HttpStatus.OK);
	}
}
