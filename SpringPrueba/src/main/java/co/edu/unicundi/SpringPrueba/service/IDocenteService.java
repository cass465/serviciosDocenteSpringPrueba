package co.edu.unicundi.SpringPrueba.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.entity.Docente;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;

@Service
public interface IDocenteService extends ICrud<Docente, Integer> {
	
	Docente obtenerPorCedula(String cedula) throws ObjectNotFoundException;

	Page<Docente> listarPorNombreOApellido(Integer nPagina, Integer cantidad, String nombre, String apellido)
			throws ListNoContentException, ParameterInvalidException;

	Page<Docente> listarPorNombreEstudiante(Integer nPagina, Integer cantidad, String nombre)
			throws ListNoContentException, ParameterInvalidException;

	Page<Docente> listarPorDireccion(Integer nPagina, Integer cantidad, String criterio, String valor)
			throws ListNoContentException, ParameterInvalidException;
	
}
