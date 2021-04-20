package co.edu.unicundi.SpringPrueba.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.entity.Docente;
import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.exception.RegisteredObjectException;

@Service
public interface IDocenteService {
	
	void crear(Docente docente) throws RegisteredObjectException;
	
	void editar(Docente docente) throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException;
	
	void eliminar(Integer id) throws ObjectNotFoundException;
	
	Page<Docente> listar(Integer nPagina, Integer cantidad, String orden) throws ListNoContentException, ParameterInvalidException;
	
	Docente obtenerPorId(Integer id) throws ObjectNotFoundException;
	
	Docente obtenerPorCedula(String cedula) throws ObjectNotFoundException;

	Page<Docente> listarPorNombreOApellido(Integer nPagina, Integer cantidad, String nombre, String apellido)
			throws ListNoContentException, ParameterInvalidException;

	Page<Docente> listarPorNombreEstudiante(Integer nPagina, Integer cantidad, String nombre)
			throws ListNoContentException, ParameterInvalidException;
	
}
