package co.edu.unicundi.SpringPrueba.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.view.GrupoDocenteView;

@Service
public interface IGrupoDocenteViewService {

	Page<GrupoDocenteView> listar(Integer nPagina, Integer cantidad) throws ListNoContentException, ParameterInvalidException; 
	
}
