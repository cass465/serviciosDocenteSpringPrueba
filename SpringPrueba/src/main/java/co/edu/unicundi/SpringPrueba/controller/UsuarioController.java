package co.edu.unicundi.SpringPrueba.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicundi.SpringPrueba.entity.Estudiante;
import co.edu.unicundi.SpringPrueba.entity.Usuario;
import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.exception.RegisteredObjectException;
import co.edu.unicundi.SpringPrueba.service.IUsuarioService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usuarios")
@Api(value = "Users microservice", description = "Gestion de los usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@PostMapping(path = "/crear")
	@ApiOperation(value = "Crear usuario", notes = "Crear un usuario")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Creado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "La cedula ya existe"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Campo cedula requerido") })
	public ResponseEntity<?> crear(
			@ApiParam(name = "Usuario", type = "Object", value = "Objeto usuario con sus datos", required = true) @Valid @RequestBody Usuario usuario)
			throws RegisteredObjectException, FieldRequiredException, ObjectNotFoundException {

		usuarioService.crear(usuario);
		return new ResponseEntity<String>("Usuario registrado correctamente", HttpStatus.CREATED);
	}

	@PutMapping(path = "/editar")
	@ApiOperation(value = "Editar usuario", notes = "Editar a un usuario")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "Editado correctamente"),
			@ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "La cedula ya existe"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "El id del usuario no existe"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Campo cedula e id requeridos") })
	public ResponseEntity<?> editar(
			@ApiParam(name = "Usuario", type = "Object", value = "Objeto usuario con los datos a editar", required = true) @Valid @RequestBody Usuario usuario)
			throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException {

		usuarioService.editar(usuario);
		return new ResponseEntity<String>("Usuario editado correctamente", HttpStatus.OK);
	}

	@GetMapping(path = "/listar/{nPagina}/{cantidad}/{orden}")
	@ApiOperation(value = "Listar usuarios", notes = "Lista todos los usuarios según el paginado", response = Estudiante.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "La lista trae uno o más usuarios"),
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "La lista está vacia") })
	public ResponseEntity<?> listar(
			@ApiParam(name = "numero página", type = "Integer", value = "Número de la página a listar", required = true) @PathVariable Integer nPagina,
			@ApiParam(name = "tamaño página", type = "Integer", value = "Cantitdad de datos dentro de la página a listar", required = true) @PathVariable Integer cantidad,
			@ApiParam(name = "orden de listado", type = "String", value = "Orden del listado (ascendente o descendente)", required = true) @PathVariable String orden)
			throws ListNoContentException, ParameterInvalidException {

		Page<Usuario> usuarios = usuarioService.listar(nPagina, cantidad, orden);
		return new ResponseEntity<Page<Usuario>>(usuarios, HttpStatus.OK);
	}

	@GetMapping(path = "/obtenerPorId/{id}")
	@ApiOperation(value = "Obtener por id", notes = "Obtiene al usuario filtrando por id")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "Usuario encontrado"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se encontro el usuario") })
	public ResponseEntity<?> obtenerPorId(
			@ApiParam(name = "id", type = "Integer", value = "Id del usuario a obtener", required = true) @PathVariable Integer id)
			throws ObjectNotFoundException {

		Usuario usuario = usuarioService.obtenerPorId(id);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

}
