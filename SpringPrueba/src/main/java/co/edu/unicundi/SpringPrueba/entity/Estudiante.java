package co.edu.unicundi.SpringPrueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Modelo Estudiante")
@Entity
@Table(name = "estudiante")
public class Estudiante {

	@ApiModelProperty(value = "Id del estudiante", required = false, allowableValues = "range[1, 100]")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty(value = "Cedula del estudiante", required = true, allowableValues = "range[7, 10]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^([0-9])*$", message = "Solo valores numéricos")
	@Size(min = 7, max = 10, message = "La longitud debe estar entre 7 y 10")
	@Column(name = "cedula", length = 10, nullable = false, unique = true)
	private String cedula;
	
	@ApiModelProperty(value = "Nombre del estudiante", required = true, allowableValues = "range[2, 30]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato incorrecto")
	@Size(min = 2, max = 30, message = "La longitud debe estar entre 2 y 30")
	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;

	@ApiModelProperty(value = "Apellido del estudiante", required = true, allowableValues = "range[2, 30]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato incorrecto")
	@Size(min = 2, max = 30, message = "La longitud debe estar entre 2 y 30")
	@Column(name = "apellido", length = 30, nullable = false)
	private String apellido;
	
	@ManyToOne
	@JoinColumn(name = "id_docente", nullable = false)
	private Docente docente;

	public Estudiante() {

	}

	/**
	 * @param cedula
	 * @param nombre
	 * @param apellido
	 * @param docente
	 */
	public Estudiante(
			@NotNull(message = "Campo requerido") @Pattern(regexp = "^([0-9])*$", message = "Solo valores numéricos") @Size(min = 7, max = 10, message = "La longitud debe estar entre 7 y 10") String cedula,
			@NotNull(message = "Campo requerido") @Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato incorrecto") @Size(min = 2, max = 30, message = "La longitud debe estar entre 2 y 30") String nombre,
			@NotNull(message = "Campo requerido") @Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato incorrecto") @Size(min = 2, max = 30, message = "La longitud debe estar entre 2 y 30") String apellido,
			Docente docente) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.docente = docente;
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
	 * @return the docente
	 */
	public Docente getDocente() {
		return docente;
	}

	/**
	 * @param docente the docente to set
	 */
	public void setDocente(Docente docente) {
		this.docente = docente;
	}	
}

