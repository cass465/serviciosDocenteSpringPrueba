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

@ApiModel("Modelo Usuario")
@Entity
@Table(name = "usuario")
public class Usuario {

	@ApiModelProperty(value = "Id del usuario", required = false, allowableValues = "range[1, 100]")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ApiModelProperty(value = "Cedula del usuario", required = true, allowableValues = "range[7, 10]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^([0-9])*$", message = "Solo valores numéricos")
	@Size(min = 7, max = 10, message = "La longitud debe estar entre 7 y 10")
	@Column(name = "cedula", length = 10, nullable = false, unique = true)
	private String cedula;

	@ApiModelProperty(value = "Nombre del usuario", required = true, allowableValues = "range[2, 30]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato incorrecto")
	@Size(min = 2, max = 30, message = "La longitud debe estar entre 2 y 30")
	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;

	@ApiModelProperty(value = "Apellido del usuario", required = true, allowableValues = "range[2, 30]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato incorrecto")
	@Size(min = 2, max = 30, message = "La longitud debe estar entre 2 y 30")
	@Column(name = "apellido", length = 30, nullable = false)
	private String apellido;
	
	@ApiModelProperty(value = "Clave del usuario", required = true)
	@NotNull(message = "Campo requerido")
	@Size(min = 6, message = "La longitud debe tener como mínimo 6 caracteres")
	@Column(columnDefinition = "TEXT", name = "clave", nullable = false)
	private String clave;
	
	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false)
	private Rol rol;
	
	public Usuario () {
		
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
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the rol
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
}
