package co.edu.unicundi.SpringPrueba.service.imp;

import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.dto.Estudiante;
import co.edu.unicundi.SpringPrueba.dto.Universidad;
import co.edu.unicundi.SpringPrueba.service.IEstudianteService;

@Service
public class EstudianteServiceImp implements IEstudianteService{

	@Override
	public Estudiante obtener() {
		Universidad universidad = new Universidad("UdeC");
		Estudiante estudiante = new Estudiante(0, "Camilo Andrés", "Sanabria", universidad);
		return estudiante;
	}

}
