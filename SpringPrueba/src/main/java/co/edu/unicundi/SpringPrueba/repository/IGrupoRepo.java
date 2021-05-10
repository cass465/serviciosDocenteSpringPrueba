package co.edu.unicundi.SpringPrueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.SpringPrueba.entity.Grupo;

@Repository
public interface IGrupoRepo extends JpaRepository<Grupo, Integer>, PagingAndSortingRepository<Grupo, Integer> {
	
	
}