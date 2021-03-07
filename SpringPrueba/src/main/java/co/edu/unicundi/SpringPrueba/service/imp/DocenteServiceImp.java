package co.edu.unicundi.SpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.dto.Docente;
import co.edu.unicundi.SpringPrueba.service.IDocenteService;

@Service
public class DocenteServiceImp implements IDocenteService {

	private List<Docente> docentes;

	DocenteServiceImp() {
		docentes = new ArrayList<Docente>();
	}

	@Override
	public String crear(Docente docente) {
		if (docente.getCedula() != null) {
			if (validarCedulaExistente(docente.getCedula(), null)) {
				docente = asignarId(docente);
				docentes.add(docente);
				return "Docente registrado correctamente";
			} else {
				return "La cedula ingresada ya existe";
			}
		} else {
			return "Campo cedula requerido";
		}
	}

	@Override
	public String editar(Docente docente) {
		if (docente.getId() != null) {
			if (docente.getCedula() != null) {
				if (validarIdDocente(docente.getId())) {
					if (validarCedulaExistente(docente.getCedula(), docente)) {
						for (int i = 0; i < docentes.size(); i++) {
							if (docentes.get(i).getId().equals(docente.getId())) {
								docentes.set(i, docente);
							}
						}
						return "Docente editado correctamente";
					} else {
						return "La cedula ingresada ya existe";
					}
				} else {
					return "El id del docente no existe";
				}
			} else {
				return "Campo cedula requerido";
			}
		} else {
			return "Campo id requerido";
		}
	}

	@Override
	public String eliminar(Integer id) {
		if (validarIdDocente(id)) {
			for (int i = 0; i < docentes.size(); i++) {
				if (docentes.get(i).getId().equals(id)) {
					docentes.remove(i);
				}
			}
			return "Docente eliminado correctamente";
		} else {
			return "El id del docente no existe";
		}
	}

	@Override
	public List<Docente> listar() {
		return docentes;
	}

	@Override
	public Docente obtenerPorId(Integer id) {
		Docente docente = new Docente();
		for (int i = 0; i < docentes.size(); i++) {
			if (docentes.get(i).getId().equals(id)) {
				docente = docentes.get(i);
			}
		}
		return docente;
	}
	
	/** Metodos de logica **/
	private Docente asignarId(Docente docente) {
		int nDocentes = 0;
		for (int i = 0; i < docentes.size(); i++) {
			nDocentes = docentes.get(i).getId();
		}
		docente.setId(nDocentes + 1);
		return docente;
	}

	private boolean validarCedulaExistente(String cedula, Docente docenteEditar) {
		boolean validacion = true;
		for (Docente docente : docentes) {
			if (docente.getCedula().equals(cedula)) {
				if (docenteEditar == null) { // Si la validacion es para crear
					validacion = false;
				} else { // Si la validacion es para editar
					if (docenteEditar.getId() == docente.getId()) {
						validacion = true;
					} else {
						validacion = false;
					}
				}
			}
		}
		return validacion;
	}
	
	private boolean validarIdDocente(int id) {
		boolean validacion = false;
		for (Docente docente : docentes) {
			if (docente.getId().equals(id)) {
				validacion = true;
			}
		}
		return validacion;
	}

}
