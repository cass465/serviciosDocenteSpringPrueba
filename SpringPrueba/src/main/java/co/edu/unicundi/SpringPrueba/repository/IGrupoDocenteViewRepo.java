package co.edu.unicundi.SpringPrueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.SpringPrueba.view.GrupoDocenteView;

@Repository
public interface IGrupoDocenteViewRepo extends JpaRepository<GrupoDocenteView, Integer>, PagingAndSortingRepository<GrupoDocenteView, Integer>{
	
}
