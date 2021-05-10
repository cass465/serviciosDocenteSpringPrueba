package co.edu.unicundi.SpringPrueba.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.repository.IGrupoDocenteViewRepo;
import co.edu.unicundi.SpringPrueba.service.IGrupoDocenteViewService;
import co.edu.unicundi.SpringPrueba.view.GrupoDocenteView;

@Service
public class GrupoDocenteViewServiceImp implements IGrupoDocenteViewService {

	@Autowired
	private IGrupoDocenteViewRepo grupoDocenteViewRepo;

	@Override
	public Page<GrupoDocenteView> listar(Integer nPagina, Integer cantidad) throws ListNoContentException, ParameterInvalidException {
		
		if (nPagina > -1 && cantidad > 0) {
			PageRequest pageRequest = PageRequest.of(nPagina, cantidad, Sort.by("cedula").ascending());
			
			Page<GrupoDocenteView> page = grupoDocenteViewRepo.findAll(pageRequest);
			if (page.getContent().size() > 0) {
				return page;
			} else {
				throw new ListNoContentException();
			}
		} else if (nPagina < 0 && cantidad > 0) {
			throw new ParameterInvalidException("El número de página debe ser mínimo 0");
		} else if (nPagina > -1 && cantidad < 1) {
			throw new ParameterInvalidException("La cantidad de datos de página debe ser mínimo 1");
		} else {
			throw new ParameterInvalidException(
					"El número de página debe ser minimo 0 y la cantidad de datos debe ser mínimo 1");
		}
	}
}
