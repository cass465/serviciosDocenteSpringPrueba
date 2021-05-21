package co.edu.unicundi.SpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import co.edu.unicundi.SpringPrueba.repository.IGrupoRepo;
import co.edu.unicundi.SpringPrueba.service.IGrupoDocenteService;

@Service
public class GrupoDocenteServiceImp implements IGrupoDocenteService {

	@Autowired
	private IGrupoDocenteRepo grupoDocenteRepo;

	@Autowired
	private IDocenteRepo docenteRepo;

	@Autowired
	private IGrupoRepo grupoRepo;

	GrupoDocenteServiceImp() {

	}

	@Override
	public void crear(GrupoDocente entity)
			throws RegisteredObjectException, FieldRequiredException, ObjectNotFoundException {

		Docente docente = docenteRepo.findById(entity.getDocente().getId())
				.orElseThrow(() -> new ObjectNotFoundException("El id del docente no existe"));

		Grupo grupo = grupoRepo.findById(entity.getGrupo().getId())
				.orElseThrow(() -> new ObjectNotFoundException("El id del grupo no existe"));

		if (grupoDocenteRepo.findByGrupoIdAndDocenteId(grupo.getId(), docente.getId()) == null) {
			grupoDocenteRepo.crear(entity.getJornada(), entity.getDocente().getId(), entity.getGrupo().getId());
			;
		} else {
			throw new RegisteredObjectException("La relación ya se encuentra registrada");
		}
	}

	@Override
	public void editar(GrupoDocente entity)
			throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException {

		Docente docente = docenteRepo.findById(entity.getDocente().getId())
				.orElseThrow(() -> new ObjectNotFoundException("El id del docente no existe"));

		Grupo grupo = grupoRepo.findById(entity.getGrupo().getId())
				.orElseThrow(() -> new ObjectNotFoundException("El id del grupo no existe"));

		if (grupoDocenteRepo.findByGrupoIdAndDocenteId(grupo.getId(), docente.getId()) != null) {
			grupoDocenteRepo.editar(entity.getJornada(), entity.getDocente().getId(), entity.getGrupo().getId());
		} else {
			throw new ObjectNotFoundException("La relación no se encuentra registrada");
		}
	}

	@Override
	public void eliminar(Integer idDocente, Integer idGrupo) throws ObjectNotFoundException {

		GrupoDocente grupoDocente = grupoDocenteRepo.findByGrupoIdAndDocenteId(idGrupo, idDocente);

		if (grupoDocente != null) {
			grupoDocenteRepo.eliminar(idDocente, idGrupo);
		} else {
			throw new ObjectNotFoundException("La relación no se encuentra registrada");
		}
	}

	@Override
	public void eliminar(Integer id) throws ObjectNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<GrupoDocente> listar(Integer nPagina, Integer cantidad, String orden)
			throws ListNoContentException, ParameterInvalidException {
		if (orden.equals("ASC") || orden.equals("DESC")) {
			if (nPagina > -1 && cantidad > 0) {
				PageRequest pageRequest;
				if (orden.equals("ASC")) {
					pageRequest = PageRequest.of(nPagina, cantidad, Sort.by("jornada").ascending());
				} else {
					pageRequest = PageRequest.of(nPagina, cantidad, Sort.by("jornada").descending());
				}

				Page<GrupoDocente> page = grupoDocenteRepo.findAll(pageRequest);
				if (page.getContent().size() > 0) {
					for (GrupoDocente gd : page.getContent()) {
						gd.getDocente().setEstudiantes(null);
						gd.getDocente().getDireccion().setDocente(null);
					}

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

		} else {
			throw new ParameterInvalidException("Parámetro orden inválido, debe ser 'ASC' o 'DESC'");
		}
	}

	@Override
	public GrupoDocente obtenerPorId(Integer id) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocenteGruposDTO obtenerGruposPorIdDocente(Integer idDocente) throws ObjectNotFoundException {
		Docente docente = docenteRepo.findById(idDocente)
				.orElseThrow(() -> new ObjectNotFoundException("El id del docente no existe"));

		docente.getDireccion().setDocente(null);
		List<GrupoDocente> gruposDocente = grupoDocenteRepo.listarPorIdDocente(idDocente);
		List<Grupo> grupos = new ArrayList<Grupo>();

		for (GrupoDocente gd : gruposDocente) {
			grupos.add(gd.getGrupo());
		}

		return new DocenteGruposDTO(idDocente, docente.getCedula(), docente.getNombre(), docente.getApellido(),
				docente.getCorreo(), docente.getDireccion(), grupos);

	}
}
