package co.edu.unicundi.SpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.entity.Docente;
import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.LoginException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.exception.RegisteredObjectException;
import co.edu.unicundi.SpringPrueba.repository.IUsuarioRepo;
import co.edu.unicundi.SpringPrueba.service.IUsuarioService;
import co.edu.unicundi.SpringPrueba.entity.Usuario;

@Service
public class UsuarioServiceImp implements IUsuarioService, UserDetailsService {

	@Autowired
	private IUsuarioRepo usuarioRepo;

	public UsuarioServiceImp() {

	}

	@Override
	public void crear(Docente entity)
			throws RegisteredObjectException, FieldRequiredException, ObjectNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void editar(Docente entity)
			throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(Integer id) throws ObjectNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Docente> listar(Integer nPagina, Integer cantidad, String orden)
			throws ListNoContentException, ParameterInvalidException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Docente obtenerPorId(Integer id) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cambiarEstado(String estado, int id) throws ObjectNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findOneByNick(username);

		
		/*if(usuario == null) 
			throw new LoginException("Usuario no encontrado");
		*/
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));

		UserDetails ud = new User(usuario.getNick(), usuario.getClave(), roles);
		return ud;
	}

}
