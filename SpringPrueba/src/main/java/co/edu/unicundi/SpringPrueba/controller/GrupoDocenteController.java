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

import co.edu.unicundi.SpringPrueba.entity.Docente;
import co.edu.unicundi.SpringPrueba.entity.GrupoDocente;
import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.exception.RegisteredObjectException;
import co.edu.unicundi.SpringPrueba.service.IGrupoDocenteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/gruposDocentes")
@Api(value = "Users microservice", description = "Gestion de los docentes")
public class GrupoDocenteController {
	
	@Autowired
	private IGrupoDocenteService grupoDocenteService;
	
	@PostMapping(path = "/crear")
	@ApiOperation(value = "Crear relacion grupo docente", notes = "Crear relacion grupo docente")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Creado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Grupo y/o docente no encontrados o relación ya existente") })
	public ResponseEntity<?> crear(
			@ApiParam(name = "GrupoDocente", type = "Object", value = "Objeto grupo docente", required = true) @Valid @RequestBody GrupoDocente grupoDocente)
			throws RegisteredObjectException, FieldRequiredException, ObjectNotFoundException {

		grupoDocenteService.crear(grupoDocente);
		return new ResponseEntity<String>("Relación Grupo Docente registrada correctamente", HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/editar")
	@ApiOperation(value = "Editar docente", notes = "Editar a un docente de la lista si existe")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "Editado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "La cedula ya existe"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "El id del docente no existe"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Campo cedula y id requeridos") })
	public ResponseEntity<?> editar(
			@ApiParam(name = "Docente", type = "Object", value = "Objeto docente con los datos a editar", required = true) @Valid @RequestBody GrupoDocente grupoDocente)
			throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException {

		grupoDocenteService.editar(grupoDocente);
		return new ResponseEntity<String>("Relación Grupo Docente editada correctamente", HttpStatus.OK);
	}

	@DeleteMapping(path = "/eliminar/{idDocente}/{idGrupo}")
	@ApiOperation(value = "Eliminar docente", notes = "Eliminar a un docente de la lista si existe")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Eliminado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "El id del docente no existe") })
	public ResponseEntity<?> eliminar(
			@ApiParam(name = "idDocente", type = "Integer", value = "Id del docente de la relacion a eliminar", required = true) @PathVariable Integer idDocente,
			@ApiParam(name = "idGrupo", type = "Integer", value = "Id del grupo de la relacion a eliminar", required = true) @PathVariable Integer idGrupo)
			throws ObjectNotFoundException {

		grupoDocenteService.eliminar(idDocente, idGrupo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/listar/{nPagina}/{cantidad}/{orden}")
	@ApiOperation(value = "Listar docentes", notes = "Lista todos los docentes según el paginado", response = Docente.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "La lista trae uno o más docentes"),
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "La lista está vacia") })
	public ResponseEntity<?> listar(
			@ApiParam(name = "numero página", type = "Integer", value = "Número de la página a listar", required = true) @PathVariable Integer nPagina,
			@ApiParam(name = "tamaño página", type = "Integer", value = "Cantitdad de datos dentro de la página a listar", required = true) @PathVariable Integer cantidad,
			@ApiParam(name = "orden de listado", type = "String", value = "Orden del listado (ascendente o descendente)", required = true) @PathVariable String orden)
			throws ListNoContentException, ParameterInvalidException {

		Page<GrupoDocente> gruposDocentes = grupoDocenteService.listar(nPagina, cantidad, orden);
		return new ResponseEntity<Page<GrupoDocente>>(gruposDocentes, HttpStatus.OK);
	}
}
