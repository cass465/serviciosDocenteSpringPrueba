package co.edu.unicundi.SpringPrueba.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.entity.Docente;
import co.edu.unicundi.SpringPrueba.entity.Estudiante;
import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.exception.RegisteredObjectException;
import co.edu.unicundi.SpringPrueba.repository.IDocenteRepo;
import co.edu.unicundi.SpringPrueba.repository.IEstudianteRepo;
import co.edu.unicundi.SpringPrueba.service.IEstudianteService;

@Service
public class EstudianteServiceImp implements IEstudianteService {

	@Autowired
	private IEstudianteRepo estudianteRepo;

	@Autowired
	private IDocenteRepo docenteRepo;

	EstudianteServiceImp() {

	}

	@Override
	public void crear(Estudiante estudiante)
			throws RegisteredObjectException, FieldRequiredException, ObjectNotFoundException {
		estudiante.setId(null);

		if (estudiante.getDocente() != null && estudiante.getDocente().getId() != null) {
			docenteRepo.findById(estudiante.getDocente().getId())
					.orElseThrow(() -> new ObjectNotFoundException("El id del docente no existe"));
			Estudiante estudianteCedula = estudianteRepo.findByCedula(estudiante.getCedula());

			if (estudianteCedula == null) {
				estudianteRepo.save(estudiante);
			} else {
				throw new RegisteredObjectException("La cedula ingresada ya existe");
			}
		} else {
			throw new FieldRequiredException("Id de docente requerido en objeto docente");
		}
	}

	@Override
	public void editar(Estudiante estudiante)
			throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException {
		if (estudiante.getId() != null) {
			if (estudiante.getDocente() != null && estudiante.getDocente().getId() != null) {
				Docente docenteId = docenteRepo.findById(estudiante.getDocente().getId())
						.orElseThrow(() -> new ObjectNotFoundException("El id del docente no existe"));

				Estudiante estudianteId = estudianteRepo.findById(estudiante.getId())
						.orElseThrow(() -> new ObjectNotFoundException("El id del estudiante no existe"));

				Integer nEstudiantesCedula = estudianteRepo.contarEstudiantesPorCedula(estudiante.getId(),
						estudiante.getCedula());

				if (nEstudiantesCedula == 0) {

					estudianteId.setCedula(estudiante.getCedula());
					estudianteId.setNombre(estudiante.getNombre());
					estudianteId.setApellido(estudiante.getApellido());
					estudianteId.setDocente(docenteId);

					estudianteRepo.save(estudianteId);
				} else {
					throw new RegisteredObjectException("La cedula ingresada ya existe");
				}
			} else {
				throw new FieldRequiredException("Id de docente requerido en objeto docente");
			}
		} else {
			throw new FieldRequiredException("ID: Campo requerido");
		}
	}

	@Override
	public void eliminar(Integer id) throws ObjectNotFoundException {
		Estudiante estudianteId = estudianteRepo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("El id del estudiante no existe"));

		estudianteRepo.delete(estudianteId);
	}

	@Override
	public Page<Estudiante> listar(Integer nPagina, Integer cantidad, String orden)
			throws ListNoContentException, ParameterInvalidException {

		if (orden.equals("ASC") || orden.equals("DESC")) {
			if (nPagina > -1 && cantidad > 0) {
				PageRequest pageRequest;
				if (orden.equals("ASC")) {
					pageRequest = PageRequest.of(nPagina, cantidad, Sort.by("nombre").ascending());
				} else {
					pageRequest = PageRequest.of(nPagina, cantidad, Sort.by("nombre").descending());
				}
				Page<Estudiante> page = estudianteRepo.findAll(pageRequest);
				if (page.getContent().size() > 0) {
					for (Estudiante estudiante : page.getContent()) {
						estudiante.setDocente(null);
					}

					return page;
				} else {
					throw new ListNoContentException();
				}
			} else if (nPagina < 0 && cantidad > 0) {
				throw new ParameterInvalidException("El n??mero de p??gina debe ser m??nimo 0");
			} else if (nPagina > -1 && cantidad < 1) {
				throw new ParameterInvalidException("La cantidad de datos de p??gina debe ser m??nimo 1");
			} else {
				throw new ParameterInvalidException(
						"El n??mero de p??gina debe ser minimo 0 y la cantidad de datos debe ser m??nimo 1");
			}
		} else {
			throw new ParameterInvalidException("Par??metro orden inv??lido, debe ser 'ASC' o 'DESC'");
		}
	}

	@Override
	public Estudiante obtenerPorId(Integer id) throws ObjectNotFoundException {
		Estudiante estudiante = estudianteRepo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("El id del estudiante no existe"));
		estudiante.setDocente(null);
		return estudiante;
	}
	
	@Override
	public Page<Estudiante> listarPorNombreOApellido(Integer nPagina, Integer cantidad, String nombre, String apellido)
			throws ListNoContentException, ParameterInvalidException {

		if (nPagina > -1 && cantidad > 0) {
			PageRequest pageRequest = PageRequest.of(nPagina, cantidad);
			Page<Estudiante> page = estudianteRepo.findByNombreOrApellidoOrderByIdDesc(nombre, apellido, pageRequest);
			if (page.getContent().size() > 0) {
				for (Estudiante estudiante : page.getContent()) {
					estudiante.setDocente(null);
				}

				return page;
			} else {
				throw new ListNoContentException();
			}
		} else if (nPagina < 0 && cantidad > 0) {
			throw new ParameterInvalidException("El n??mero de p??gina debe ser m??nimo 0");
		} else if (nPagina > -1 && cantidad < 1) {
			throw new ParameterInvalidException("La cantidad de datos de p??gina debe ser m??nimo 1");
		} else {
			throw new ParameterInvalidException(
					"El n??mero de p??gina debe ser minimo 0 y la cantidad de datos debe ser m??nimo 1");
		}
	}

	@Override
	public Page<Estudiante> listarPorIdDocente(Integer nPagina, Integer cantidad, Integer idDocente)
			throws ListNoContentException, ParameterInvalidException {

		if (nPagina > -1 && cantidad > 0) {
			PageRequest pageRequest = PageRequest.of(nPagina, cantidad);
			Page<Estudiante> page = estudianteRepo.findByDocenteId(idDocente, pageRequest);
			if (page.getContent().size() > 0) {
				for (Estudiante estudiante : page.getContent()) {
					estudiante.setDocente(null);
				}

				return page;
			} else {
				throw new ListNoContentException();
			}
		} else if (nPagina < 0 && cantidad > 0) {
			throw new ParameterInvalidException("El n??mero de p??gina debe ser m??nimo 0");
		} else if (nPagina > -1 && cantidad < 1) {
			throw new ParameterInvalidException("La cantidad de datos de p??gina debe ser m??nimo 1");
		} else {
			throw new ParameterInvalidException(
					"El n??mero de p??gina debe ser minimo 0 y la cantidad de datos debe ser m??nimo 1");
		}
	}
}
