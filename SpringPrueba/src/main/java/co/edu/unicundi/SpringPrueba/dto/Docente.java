package co.edu.unicundi.SpringPrueba.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Modelo Docente")
public class Docente {

	@ApiModelProperty(value = "Id del docente", required = false)
	private Integer id;

	@ApiModelProperty(value = "Cedula del docente", required = true)
	private String cedula;

	@ApiModelProperty(value = "Nombre del docente", required = false)
	private String nombre;

	@ApiModelProperty(value = "Apellido del docente", required = false)
	private String apellido;

	public Docente() {

	}

	/**
	 * @param id
	 * @param nombre
	 * @param apellido
	 */
	public Docente(Integer id, String cedula, String nombre, String apellido) {
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
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
	 * @param nombre the cedula to set
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

}
