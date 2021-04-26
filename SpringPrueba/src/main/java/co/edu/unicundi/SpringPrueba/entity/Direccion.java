package co.edu.unicundi.SpringPrueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "direccion")
public class Direccion {
	@ApiModelProperty(value = "Id del docente", required = false, allowableValues = "range[1, 100]")
	@Id
	@Column(name = "id")
	private Integer id;

	@ApiModelProperty(value = "Detalle de la direccion", required = true, allowableValues = "range[2, 100]")
	@NotNull(message = "Campo requerido")
	//@Pattern(regexp = "^([0-9])*$", message = "Solo valores numéricos")
	@Size(min = 2, max = 100, message = "La longitud debe estar entre 2 y 100")
	@Column(name = "detalle", length = 10, nullable = false, unique = true)
	private String detalle;

	@ApiModelProperty(value = "Barrio de la direccion", required = true, allowableValues = "range[2, 30]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato incorrecto")
	@Size(min = 2, max = 30, message = "La longitud debe estar entre 2 y 30")
	@Column(name = "barrio", length = 30, nullable = false)
	private String barrio;

	@ApiModelProperty(value = "Ciudad de la direccion", required = true, allowableValues = "range[2, 30]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato incorrecto")
	@Size(min = 2, max = 30, message = "La longitud debe estar entre 2 y 30")
	@Column(name = "ciudad", length = 30, nullable = false)
	private String ciudad;

	@ApiModelProperty(value = "País de la direccion", required = true, allowableValues = "range[2, 30]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato incorrecto")
	@Size(min = 2, max = 30, message = "La longitud debe estar entre 2 y 30")
	@Column(name = "pais", length = 30, nullable = false)
	private String pais;
	
	@OneToOne
	@MapsId
	private Docente docente;
	
	public Direccion() {
		
	}

	/**
	 * @param detalle
	 * @param barrio
	 * @param ciudad
	 * @param pais
	 */
	public Direccion(
			String detalle, String barrio, String ciudad, String pais) {
		this.detalle = detalle;
		this.barrio = barrio;
		this.ciudad = ciudad;
		this.pais = pais;
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
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	/**
	 * @return the barrio
	 */
	public String getBarrio() {
		return barrio;
	}

	/**
	 * @param nombre the barrio to set
	 */
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
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
