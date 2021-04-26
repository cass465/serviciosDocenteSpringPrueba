package co.edu.unicundi.SpringPrueba.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.entity.Estudiante;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;

@Service
public interface IEstudianteService extends ICrud<Estudiante, Integer> {

	Page<Estudiante> listarPorNombreOApellido(Integer nPagina, Integer cantidad, String nombre, String apellido)
			throws ListNoContentException, ParameterInvalidException;

	Page<Estudiante> listarPorIdDocente(Integer nPagina, Integer cantidad, Integer idDocente)
			throws ListNoContentException, ParameterInvalidException;

}
