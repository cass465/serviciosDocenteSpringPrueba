package co.edu.unicundi.SpringPrueba.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "grupo_docente_view")
public class GrupoDocenteView {
	
	@Id
    private Integer id;
    
    @Column(name = "cedula", nullable = false, unique = true, length = 10)
    private String cedula;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 30)
    private String apellido;
    
    @Column(name = "grupos", nullable = false)
    private Integer grupos;
    
    GrupoDocenteView(){
    	
    }

	/**
	 * @param id
	 * @param cedula
	 * @param nombre
	 * @param apellido
	 * @param grupos
	 */
	public GrupoDocenteView(Integer id, String cedula, String nombre, String apellido, Integer grupos) {
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
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
	 * @return the grupos
	 */
	public Integer getGrupos() {
		return grupos;
	}

	/**
	 * @param grupos the grupos to set
	 */
	public void setGrupos(Integer grupos) {
		this.grupos = grupos;
	}
	
}
