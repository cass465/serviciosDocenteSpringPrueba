package co.edu.unicundi.SpringPrueba.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Modelo Docente")
public class Docente {

	@ApiModelProperty(value = "Id del docente", required = false, allowableValues = "range[1, 100]")
	private Integer id;

	@ApiModelProperty(value = "Cedula del docente", required = true, allowableValues = "range[7, 10]")
	@NotNull(message = "Campo cedula requerido")
	@Pattern(regexp = "^([0-9])*$", message = "Formato de cedula incorrecto, indicar valores numéricos sin espacios")
	@Size(min = 7, max = 10, message = "La longitud de la cedula debe estar entre 7 y 10")
	private String cedula;

	@ApiModelProperty(value = "Nombre del docente", required = false, allowableValues = "range[3, 15]")
	@NotNull(message = "Campo nombre requerido")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato de nombre incorrecto")
	@Size(min = 2, max = 30, message = "Longitud de nombre no valido")
	private String nombre;

	@ApiModelProperty(value = "Apellido del docente", required = false, allowableValues = "range[3, 10]")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato de apellido incorrecto")
	@Size(min = 2, max = 30, message = "Longitud de apellido no valido")
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
