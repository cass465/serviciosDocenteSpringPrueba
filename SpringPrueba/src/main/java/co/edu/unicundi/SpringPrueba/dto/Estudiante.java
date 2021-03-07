package co.edu.unicundi.SpringPrueba.dto;

public class Estudiante {
	private Integer id;
	private String nombre;
	private String apellido;
	private Universidad universidad;
	
	public Estudiante() {
		
	}
	
	public Estudiante(Integer id, String nombre, String apellido, Universidad universidad) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.universidad = universidad;
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
	 * @return the universidad
	 */
	public Universidad getUniversidad() {
		return universidad;
	}

	/**
	 * @param universidad the universidad to set
	 */
	public void setUniversidad(Universidad universidad) {
		this.universidad = universidad;
	}
	
}
