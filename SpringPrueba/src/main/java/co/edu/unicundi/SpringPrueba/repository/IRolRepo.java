package co.edu.unicundi.SpringPrueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.SpringPrueba.entity.Rol;

@Repository
public interface IRolRepo extends JpaRepository<Rol, Integer>, PagingAndSortingRepository<Rol, Integer> {
	
	
}