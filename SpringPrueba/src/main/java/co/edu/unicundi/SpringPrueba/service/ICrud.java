package co.edu.unicundi.SpringPrueba.service;

import org.springframework.data.domain.Page;

import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.exception.RegisteredObjectException;

public interface ICrud<T, K> {

	void crear(T entity) throws RegisteredObjectException, FieldRequiredException, ObjectNotFoundException;

	void editar(T entity) throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException;

	void eliminar(K id) throws ObjectNotFoundException;

	Page<T> listar(Integer nPagina, Integer cantidad, String orden)
			throws ListNoContentException, ParameterInvalidException;

	T obtenerPorId(K id) throws ObjectNotFoundException;

}
