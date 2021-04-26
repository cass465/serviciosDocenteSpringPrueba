package co.edu.unicundi.SpringPrueba.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Modelo Docente")
@Entity
@Table(name = "docente")
public class Docente {

	@ApiModelProperty(value = "Id del docente", required = false, allowableValues = "range[1, 100]")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty(value = "Cedula del docente", required = true, allowableValues = "range[7, 10]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^([0-9])*$", message = "Solo valores numéricos")
	@Size(min = 7, max = 10, message = "La longitud debe estar entre 7 y 10")
	@Column(name = "cedula", length = 10, nullable = false, unique = true)
	private String cedula;

	@ApiModelProperty(value = "Nombre del docente", required = true, allowableValues = "range[2, 30]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato incorrecto")
	@Size(min = 2, max = 30, message = "La longitud debe estar entre 2 y 30")
	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;

	@ApiModelProperty(value = "Apellido del docente", required = true, allowableValues = "range[2, 30]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato incorrecto")
	@Size(min = 2, max = 30, message = "La longitud debe estar entre 2 y 30")
	@Column(name = "apellido", length = 30, nullable = false)
	private String apellido;

	@ApiModelProperty(value = "Correo del docente", required = true, allowableValues = "range[1, 100]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Formato incorrecto, example@gmail.com")
	@Column(name = "correo", length = 100, nullable = false, unique = true)
	private String correo;
	
	@OneToMany(mappedBy = "docente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Estudiante> estudiantes;
	
	@Valid
	@NotNull(message = "Objeto requerido")
	@OneToOne(mappedBy = "docente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Direccion direccion;

	public Docente() {

	}

	/**
	 * @param cedula
	 * @param nombre
	 * @param apellido
	 * @param correo
	 */
	public Docente(String cedula, String nombre, String apellido, String correo) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
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
	 * @return the estudiantes
	 */
	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	/**
	 * @param estudiantes the estudiantes to set
	 */
	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
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
	
}
