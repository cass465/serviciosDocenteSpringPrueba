package co.edu.unicundi.SpringPrueba.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.SpringPrueba.entity.Docente;

@Repository
public interface IDocenteRepo extends JpaRepository<Docente, Integer>, PagingAndSortingRepository<Docente, Integer> {
	
	@Query(value = "SELECT * FROM docente d WHERE d.cedula = ?1", nativeQuery = true)
	Docente obtenerPorCedulaSQL(String cedula);

	@Query(value = "SELECT d FROM Docente d WHERE d.cedula = ?1")
	Docente obtenerPorCedulaJPQL(String cedula);
	
	Docente findByCedula(String cedula);
	
	Docente findByCorreo(String correo);
	
	@Query(value = "SELECT COUNT(d) FROM Docente d WHERE d.cedula = ?2 AND d.id <> ?1")
	Integer contarDocentesPorCedula(Integer id, String cedula);
	
	@Query(value = "SELECT COUNT(d) FROM Docente d WHERE d.correo = ?2 AND d.id <> ?1")
	Integer contarDocentesPorCorreo(Integer id, String correo);
	
	Page<Docente> findByNombreOrApellidoOrderByIdDesc(String nombre, String apellido, Pageable paginado);
	
	Page<Docente> findByEstudiantesNombre(String nombre, Pageable paginado);
}