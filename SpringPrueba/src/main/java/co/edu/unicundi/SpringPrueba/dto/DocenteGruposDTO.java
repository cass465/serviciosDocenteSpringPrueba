package co.edu.unicundi.SpringPrueba.dto;

import java.util.List;

import co.edu.unicundi.SpringPrueba.entity.Direccion;
import co.edu.unicundi.SpringPrueba.entity.Grupo;

public class DocenteGruposDTO {
	
	private Integer id;

	private String cedula;

	private String nombre;

	private String apellido;

	private String correo;
	
	private Direccion direccion;
	
	private List<Grupo> grupos;

	public DocenteGruposDTO() {
		
	}
	
	/**
	 * @param id
	 * @param cedula
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @param direccion
	 * @param grupos
	 */
	public DocenteGruposDTO(Integer id, String cedula, String nombre, String apellido, String correo,
			Direccion direccion, List<Grupo> grupos) {
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.direccion = direccion;
		this.grupos = grupos;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the direccion
	 */
	public Direccion getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the grupos
	 */
	public List<Grupo> getGrupos() {
		return grupos;
	}

	/**
	 * @param grupos the grupos to set
	 */
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	
}
