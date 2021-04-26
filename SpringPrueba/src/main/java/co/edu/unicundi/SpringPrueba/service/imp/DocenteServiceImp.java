package co.edu.unicundi.SpringPrueba.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.entity.Docente;
import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
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
	public void crear(Docente docente) throws RegisteredObjectException {
		docente.setId(null);
		Docente docenteCedula = docenteRepo.findByCedula(docente.getCedula());
		Docente docenteCorreo = docenteRepo.findByCorreo(docente.getCorreo());

		if (docenteCedula == null && docenteCorreo == null) {
			if (docente.getEstudiantes() != null) {
				docente.setEstudiantes(null);
			}

			docente.getDireccion().setDocente(docente);
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

				docenteId.setCedula(docente.getCedula());
				docenteId.setNombre(docente.getNombre());
				docenteId.setApellido(docente.getApellido());
				docenteId.setCorreo(docente.getCorreo());
				docenteId.getDireccion().setDetalle(docente.getDireccion().getDetalle());
				docenteId.getDireccion().setBarrio(docente.getDireccion().getBarrio());
				docenteId.getDireccion().setCiudad(docente.getDireccion().getCiudad());
				docenteId.getDireccion().setPais(docente.getDireccion().getPais());

				docenteRepo.save(docenteId);
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
	public Page<Docente> listar(Integer nPagina, Integer cantidad, String orden)
			throws ListNoContentException, ParameterInvalidException {

		if (orden.equals("ASC") || orden.equals("DESC")) {
			if (nPagina > -1 && cantidad > 0) {
				PageRequest pageRequest;
				if (orden.equals("ASC")) {
					pageRequest = PageRequest.of(nPagina, cantidad, Sort.by("nombre").ascending());
				} else {
					pageRequest = PageRequest.of(nPagina, cantidad, Sort.by("nombre").descending());
				}

				Page<Docente> page = docenteRepo.findAll(pageRequest);
				if (page.getContent().size() > 0) {
					for (Docente docente : page.getContent()) {
						docente.setEstudiantes(null);
						docente.getDireccion().setDocente(null);
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
	public Docente obtenerPorId(Integer id) throws ObjectNotFoundException {
		Docente docente = docenteRepo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("El id del docente no existe"));
		docente.setEstudiantes(null);
		docente.getDireccion().setDocente(null);
		return docente;
	}

	@Override
	public Docente obtenerPorCedula(String cedula) throws ObjectNotFoundException {
		Docente docenteSQL = docenteRepo.obtenerPorCedulaSQL(cedula);
		Docente docenteJPQL = docenteRepo.obtenerPorCedulaJPQL(cedula);
		Docente docente = docenteRepo.findByCedula(cedula);

		if (docente != null) {
			if (docenteSQL.equals(docenteJPQL) && docenteJPQL.equals(docente)) {
				docenteJPQL.setEstudiantes(null);
				docenteJPQL.getDireccion().setDocente(null);
				return docenteJPQL;
			} else {
				return null;
			}
		} else {
			throw new ObjectNotFoundException("La cedula del docente no existe");
		}
	}

	@Override
	public Page<Docente> listarPorNombreOApellido(Integer nPagina, Integer cantidad, String nombre, String apellido)
			throws ListNoContentException, ParameterInvalidException {

		if (nPagina > -1 && cantidad > 0) {
			PageRequest pageRequest = PageRequest.of(nPagina, cantidad);
			Page<Docente> page = docenteRepo.findByNombreIgnoreCaseOrApellidoIgnoreCaseOrderByIdDesc(nombre, apellido,
					pageRequest);
			if (page.getContent().size() > 0) {
				for (Docente docente : page.getContent()) {
					docente.setEstudiantes(null);
					docente.getDireccion().setDocente(null);
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
	}

	@Override
	public Page<Docente> listarPorNombreEstudiante(Integer nPagina, Integer cantidad, String nombre)
			throws ListNoContentException, ParameterInvalidException {

		if (nPagina > -1 && cantidad > 0) {
			PageRequest pageRequest = PageRequest.of(nPagina, cantidad);
			Page<Docente> page = docenteRepo.findByEstudiantesNombre(nombre, pageRequest);
			if (page.getContent().size() > 0) {
				for (Docente docente : page.getContent()) {
					docente.setEstudiantes(null);
					docente.getDireccion().setDocente(null);
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
	}

	@Override
	public Page<Docente> listarPorDireccion(Integer nPagina, Integer cantidad, String criterio, String valor)
			throws ListNoContentException, ParameterInvalidException {

		if (nPagina > -1 && cantidad > 0) {
			if (criterio.equals("detalle") || criterio.equals("barrio") || criterio.equals("ciudad")
					|| criterio.equals("pais")) {

				PageRequest pageRequest = PageRequest.of(nPagina, cantidad);
				Page<Docente> page = null;

				switch (criterio) {
				case "detalle":
					page = docenteRepo.findByDireccionDetalleIgnoreCase(valor, pageRequest);
					break;
				case "barrio":
					page = docenteRepo.findByDireccionBarrioIgnoreCase(valor, pageRequest);
					break;
				case "ciudad":
					page = docenteRepo.findByDireccionCiudadIgnoreCase(valor, pageRequest);
					break;
				case "pais":
					page = docenteRepo.findByDireccionPaisIgnoreCase(valor, pageRequest);
					break;
				}

				if (page.getContent().size() > 0) {
					for (Docente docente : page.getContent()) {
						docente.setEstudiantes(null);
						docente.getDireccion().setDocente(null);
					}

					return page;
				} else {
					throw new ListNoContentException();
				}
			} else {
				throw new ParameterInvalidException("Criterio de busqueda de direccion incorrecto");
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
