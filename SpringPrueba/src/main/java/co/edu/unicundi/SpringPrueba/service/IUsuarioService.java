package co.edu.unicundi.SpringPrueba.service;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.entity.Docente;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;

@Service
public interface IUsuarioService extends ICrud<Docente, Integer> {

	void cambiarEstado(String estado, int id) throws ObjectNotFoundException;
	
}
