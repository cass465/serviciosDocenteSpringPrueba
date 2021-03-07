package co.edu.unicundi.SpringPrueba.service;

import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.dto.Estudiante;

@Service
public interface IEstudianteService {

	Estudiante obtener();
	
}
