package co.edu.unicundi.SpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.dto.DocenteGruposDTO;
import co.edu.unicundi.SpringPrueba.entity.Docente;
import co.edu.unicundi.SpringPrueba.entity.Grupo;
import co.edu.unicundi.SpringPrueba.entity.GrupoDocente;
import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.exception.RegisteredObjectException;
import co.edu.unicundi.SpringPrueba.repository.IDocenteRepo;
import co.edu.unicundi.SpringPrueba.repository.IGrupoDocenteRepo;
import co.edu.unicundi.SpringPrueba.service.IGrupoDocenteService;

@Service
public class GrupoDocenteServiceImp implements IGrupoDocenteService {

	@Autowired
	private IGrupoDocenteRepo grupoDocenteRepo;

	@Autowired
	private IDocenteRepo docenteRepo;

	GrupoDocenteServiceImp() {

	}

	@Override
	public void crear(GrupoDocente entity)
			throws RegisteredObjectException, FieldRequiredException, ObjectNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void editar(GrupoDocente entity)
			throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(Integer id) throws ObjectNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<GrupoDocente> listar(Integer nPagina, Integer cantidad, String orden)
			throws ListNoContentException, ParameterInvalidException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GrupoDocente obtenerPorId(Integer id) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocenteGruposDTO listarPorIdDocente(Integer idDocente) throws ObjectNotFoundException {
		Docente docente = docenteRepo.findById(idDocente)
				.orElseThrow(() -> new ObjectNotFoundException("El id del docente no existe"));

		docente.getDireccion().setDocente(null);
		List<GrupoDocente> gruposDocente = grupoDocenteRepo.listarPorIdDocente(idDocente);
		List<Grupo> grupos = new ArrayList<Grupo>();
		
		for (GrupoDocente gd : gruposDocente) {
			grupos.add(gd.getGrupo());
		}

		return new DocenteGruposDTO(idDocente, docente.getCedula(), docente.getNombre(),
				docente.getApellido(), docente.getCorreo(), docente.getDireccion(), grupos);

	}

}
