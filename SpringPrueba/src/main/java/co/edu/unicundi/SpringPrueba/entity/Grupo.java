package co.edu.unicundi.SpringPrueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author YEISON
 *
 */
@ApiModel("Modelo Grupo")
@Entity
@Table(name = "grupo")	
public class Grupo {
	
	@ApiModelProperty(value = "Id del grupo", required = false, allowableValues = "range[1, 100]")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@ApiModelProperty(value = "Codigo del grupo", required = true, allowableValues = "range[3, 4]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^([0-9])*$", message = "Solo valores numéricos")
	@Size(min = 3, max = 4, message = "La longitud debe estar entre 3 y 4")
	@Column(name = "codigo", length = 4	, nullable = false)
	private String codigo;
	
	@ApiModelProperty(value = "Programa del grupo", required = true, allowableValues = "range[3, 50]")
	@NotNull(message = "Campo requerido")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato incorrecto")
	@Size(min = 3, max = 50, message = "La longitud debe estar entre 3 y 50")
	@Column(name = "programa", length = 50, nullable = false)
	private String programa;

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
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the programa
	 */
	public String getPrograma() {
		return programa;
	}

	/**
	 * @param programa the programa to set
	 */
	public void setPrograma(String programa) {
		this.programa = programa;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

	

	
	
	
	
	

}