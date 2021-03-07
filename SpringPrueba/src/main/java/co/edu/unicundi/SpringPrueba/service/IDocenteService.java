package co.edu.unicundi.SpringPrueba.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.dto.Docente;

@Service
public interface IDocenteService {
	String crear(Docente docente);
	String editar(Docente docente);
	String eliminar(Integer id);
	List<Docente> listar();
	Docente obtenerPorId(Integer id);
	
}
