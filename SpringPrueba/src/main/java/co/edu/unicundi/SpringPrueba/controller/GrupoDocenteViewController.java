package co.edu.unicundi.SpringPrueba.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicundi.SpringPrueba.entity.Docente;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.service.IGrupoDocenteViewService;
import co.edu.unicundi.SpringPrueba.view.GrupoDocenteView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/grupoDocenteView")
@Api(value = "Users microservice", description = "Gestion de los docentes")
public class GrupoDocenteViewController {
	
	@Autowired
	private IGrupoDocenteViewService grupoDocenteViewService;

	@GetMapping(path = "/listar/{nPagina}/{cantidad}")
	@ApiOperation(value = "Listar docentes con la cantidad de grupos de cada uno", notes = "Lista todos los docentes según el paginado", response = Docente.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "La lista trae uno o más docentes"),
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "La lista está vacia") })
	public ResponseEntity<?> listar(
			@ApiParam(name = "numero página", type = "Integer", value = "Número de la página a listar", required = true) @PathVariable Integer nPagina,
			@ApiParam(name = "tamaño página", type = "Integer", value = "Cantitdad de datos dentro de la página a listar", required = true) @PathVariable Integer cantidad)
			throws ListNoContentException, ParameterInvalidException {

		Page<GrupoDocenteView> docentes = grupoDocenteViewService.listar(nPagina, cantidad);
		return new ResponseEntity<Page<GrupoDocenteView>>(docentes, HttpStatus.OK);
	}
}
