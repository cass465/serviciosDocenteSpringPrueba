package co.edu.unicundi.SpringPrueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import co.edu.unicundi.SpringPrueba.entity.Estudiante;

@Repository
public interface IEstudianteRepo
		extends JpaRepository<Estudiante, Integer>, PagingAndSortingRepository<Estudiante, Integer> {

	Estudiante findByCedula(String cedula);
	
	@Query(value = "SELECT COUNT(e) FROM Estudiante e WHERE e.cedula = ?2 AND e.id <> ?1")
	Integer contarEstudiantesPorCedula(Integer id, String cedula);
}
