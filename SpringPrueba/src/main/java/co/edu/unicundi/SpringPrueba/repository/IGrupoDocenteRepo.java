package co.edu.unicundi.SpringPrueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.SpringPrueba.entity.GrupoDocente;

@Repository
public interface IGrupoDocenteRepo extends JpaRepository<GrupoDocente, Integer>, PagingAndSortingRepository<GrupoDocente, Integer> {
	
	@Query(value = "SELECT gd FROM GrupoDocente gd WHERE gd.docente.id = ?1")
	List<GrupoDocente> listarPorIdDocente(Integer idDocente);
	
}
