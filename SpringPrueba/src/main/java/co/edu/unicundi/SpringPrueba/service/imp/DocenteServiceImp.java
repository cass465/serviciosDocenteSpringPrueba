package co.edu.unicundi.SpringPrueba.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.entity.Docente;
import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.RegisteredObjectException;
import co.edu.unicundi.SpringPrueba.repository.IDocenteRepo;
import co.edu.unicundi.SpringPrueba.service.IDocenteService;

@Service
public class DocenteServiceImp implements IDocenteService {

	@Autowired
	private IDocenteRepo docenteRepo;

	DocenteServiceImp() {
		
	}

	@Override
	public void crear(Docente docente) throws RegisteredObjectException, FieldRequiredException {
		Docente docenteCedula = docenteRepo.findByCedula(docente.getCedula());
		Docente docenteCorreo = docenteRepo.findByCorreo(docente.getCorreo());

		if (docenteCedula == null && docenteCorreo == null) {
			docenteRepo.save(docente);
		} else if (docenteCedula == null && docenteCorreo != null) {
			throw new RegisteredObjectException("El correo ingresado ya existe");
		} else if (docenteCedula != null && docenteCorreo == null) {
			throw new RegisteredObjectException("La cedula ingresada ya existe");
		} else {
			throw new RegisteredObjectException("La cedula y el correo ingresados ya existen");
		}
	}

	@Override
	public void editar(Docente docente)
			throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException {
		if (docente.getId() != null) {

			Docente docenteId = docenteRepo.findById(docente.getId())
					.orElseThrow(() -> new ObjectNotFoundException("El id del docente no existe"));
			
			Integer nDocentesCedula = docenteRepo.contarDocentesPorCedula(docente.getId(), docente.getCedula());
			Integer nDocentesCorreo = docenteRepo.contarDocentesPorCorreo(docente.getId(), docente.getCorreo());
			
			if (nDocentesCedula == 0 && nDocentesCorreo == 0) {
				docente.setId(docenteId.getId());
				docenteRepo.save(docente);
			} else if (nDocentesCedula == 0 && nDocentesCorreo > 0) {
				throw new RegisteredObjectException("El correo ingresado ya existe");
			} else if (nDocentesCedula > 0 && nDocentesCorreo == 0) {
				throw new RegisteredObjectException("La cedula ingresada ya existe");
			} else {
				throw new RegisteredObjectException("La cedula y el correo ingresados ya existen");
			}

		} else {
			throw new FieldRequiredException("ID: Campo requerido");
		}
	}

	@Override
	public void eliminar(Integer id) throws ObjectNotFoundException {
		Docente docenteId = docenteRepo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("El id del docente no existe"));
		
		docenteRepo.delete(docenteId);
	}

	@Override
	public List<Docente> listar() throws ListNoContentException {

		List<Docente> docentes = docenteRepo.findAll();
		if (docentes.size() > 0) {
			return docentes;
		} else {
			throw new ListNoContentException();
		}
	}

	@Override
	public Docente obtenerPorId(Integer id) throws ObjectNotFoundException {
		Docente docente = docenteRepo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("El id del docente no existe"));

		return docente;
	}

	@Override
	public Docente obtenerPorCedula(String cedula) throws ObjectNotFoundException {
		Docente docenteSQL = docenteRepo.obtenerPorCedulaSQL(cedula);
		Docente docenteJPQL = docenteRepo.obtenerPorCedulaJPQL(cedula);
		Docente docente = docenteRepo.findByCedula(cedula);

		if (docente != null) {
			if (docenteSQL.equals(docenteJPQL) && docenteJPQL.equals(docente)) {
				return docenteJPQL;
			} else {
				return null;
			}
		} else {
			throw new ObjectNotFoundException("La cedula del docente no existe");
		}
	}

}
