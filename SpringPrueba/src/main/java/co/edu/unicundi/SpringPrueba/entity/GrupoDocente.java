package co.edu.unicundi.SpringPrueba.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Modelo GrupoDocente")
@Entity
@Table(name = "grupo_docente")
@IdClass(GrupoDocentePK.class)
public class GrupoDocente  {

	
	@Id
	private Grupo grupo;
	
	@Id
	private Docente docente;
	
	@ApiModelProperty(value = "Jornada en la que dicta el docente en el grupo", required = true, allowableValues = "range[3, 15]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato incorrecto")
	@Size(min = 3, max = 15, message = "La longitud debe estar entre 3 y 15")
	@Column(name = "jornada", length = 15, nullable = false)
	private String jornada;

	/**
	 * @return the grupo
	 */
	public Grupo getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
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

	/**
	 * @return the jornada
	 */
	public String getJornada() {
		return jornada;
	}

	/**
	 * @param jornada the jornada to set
	 */
	public void setJornada(String jornada) {
		this.jornada = jornada;
	}
	 
	
	
}
