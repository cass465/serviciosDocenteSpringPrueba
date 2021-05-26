package co.edu.unicundi.SpringPrueba.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.SpringPrueba.entity.Usuario;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>, PagingAndSortingRepository<Usuario, Integer>  {
	
	Usuario findOneByNick(String nick);
}
