package co.edu.unicundi.SpringPrueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicundi.SpringPrueba.entity.GrupoDocente;

@Repository
public interface IGrupoDocenteRepo extends JpaRepository<GrupoDocente, Integer>, PagingAndSortingRepository<GrupoDocente, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO grupo_docente(jornada, id_docente, id_grupo) values (?1, ?2, ?3)", nativeQuery = true)
	void crear(String jornada, Integer idDocente, Integer idGrupo);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE grupo_docente SET jornada = ?1 WHERE id_docente = ?2 AND id_grupo = ?3", nativeQuery = true)
	void editar(String jornada, Integer idDocente, Integer idGrupo);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM grupo_docente WHERE id_docente = ?1 AND id_grupo = ?1", nativeQuery = true)
	void eliminar(Integer idDocente, Integer idGrupo);
	
	@Query(value = "SELECT gd FROM GrupoDocente gd WHERE gd.docente.id = ?1")
	List<GrupoDocente> listarPorIdDocente(Integer idDocente);
	
	GrupoDocente findByGrupoIdAndDocenteId(Integer idGrupo, Integer idDocente);
	
}
