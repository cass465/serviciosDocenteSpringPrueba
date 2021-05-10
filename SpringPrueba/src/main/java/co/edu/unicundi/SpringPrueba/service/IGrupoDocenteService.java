package co.edu.unicundi.SpringPrueba.service;

import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.dto.DocenteGruposDTO;
import co.edu.unicundi.SpringPrueba.entity.GrupoDocente;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;

@Service
public interface IGrupoDocenteService extends ICrud<GrupoDocente, Integer>{

	void eliminar(Integer idDocente, Integer idGrupo) throws ObjectNotFoundException;
	
	DocenteGruposDTO obtenerGruposPorIdDocente(Integer idDocente) throws ObjectNotFoundException;
}
