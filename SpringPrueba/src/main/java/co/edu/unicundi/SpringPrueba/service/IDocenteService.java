package co.edu.unicundi.SpringPrueba.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.entity.Docente;
import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.RegisteredObjectException;

@Service
public interface IDocenteService {
	
	void crear(Docente docente) throws RegisteredObjectException, FieldRequiredException;
	
	void editar(Docente docente) throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException;
	
	void eliminar(Integer id) throws ObjectNotFoundException;
	
	List<Docente> listar() throws ListNoContentException;
	
	Docente obtenerPorId(Integer id) throws ObjectNotFoundException;
	
	Docente obtenerPorCedula(String cedula) throws ObjectNotFoundException;
	
}
