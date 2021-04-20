package co.edu.unicundi.SpringPrueba.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.entity.Estudiante;
import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.exception.RegisteredObjectException;

@Service
public interface IEstudianteService {

	void crear(Estudiante estudiante) throws RegisteredObjectException, FieldRequiredException, ObjectNotFoundException;

	void editar(Estudiante estudiante) throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException;

	void eliminar(Integer id) throws ObjectNotFoundException;

	Page<Estudiante> listar(Integer nPagina, Integer cantidad, String orden) throws ListNoContentException, ParameterInvalidException;

	Estudiante obtenerPorId(Integer id) throws ObjectNotFoundException;

	Page<Estudiante> listarPorNombreOApellido(
			Integer nPagina, Integer cantidad, String nombre, String apellido)
			throws ListNoContentException, ParameterInvalidException;

	Page<Estudiante> listarPorIdDocente(
			Integer nPagina, Integer cantidad, Integer idDocente)
			throws ListNoContentException, ParameterInvalidException;

}
