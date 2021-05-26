package co.edu.unicundi.SpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unicundi.SpringPrueba.entity.Rol;
import co.edu.unicundi.SpringPrueba.entity.Usuario;
import co.edu.unicundi.SpringPrueba.exception.FieldRequiredException;
import co.edu.unicundi.SpringPrueba.exception.ListNoContentException;
import co.edu.unicundi.SpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.SpringPrueba.exception.ParameterInvalidException;
import co.edu.unicundi.SpringPrueba.exception.RegisteredObjectException;
import co.edu.unicundi.SpringPrueba.repository.IRolRepo;
import co.edu.unicundi.SpringPrueba.repository.IUsuarioRepo;
import co.edu.unicundi.SpringPrueba.service.IUsuarioService;

@Service
public class UsuarioServiceImp implements IUsuarioService, UserDetailsService {

	@Autowired
	private IUsuarioRepo usuarioRepo;
	
	@Autowired
	private IRolRepo rolRepo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public void crear(Usuario usuario)
			throws RegisteredObjectException, FieldRequiredException, ObjectNotFoundException {
		
		usuario.setId(null);
		
		if (usuario.getRol() != null && usuario.getRol().getId() != null) {
			rolRepo.findById(usuario.getRol().getId())
					.orElseThrow(() -> new ObjectNotFoundException("El id del rol no existe"));
			Usuario usuarioCedula = usuarioRepo.findOneByCedula(usuario.getCedula());

			if (usuarioCedula == null) {
				//Encriptar la clave de usuario
				usuario.setClave(bcrypt.encode(usuario.getClave()));
				
				usuarioRepo.save(usuario);
			} else {
				throw new RegisteredObjectException("La cedula ingresada ya existe");
			}
		} else {
			throw new FieldRequiredException("Id de rol requerido en objeto rol");
		}
	}

	@Override
	public void editar(Usuario usuario)
			throws RegisteredObjectException, ObjectNotFoundException, FieldRequiredException {
		
		if (usuario.getId() != null) {
			if (usuario.getRol() != null && usuario.getRol().getId() != null) {
				Rol rolId = rolRepo.findById(usuario.getRol().getId())
						.orElseThrow(() -> new ObjectNotFoundException("El id del rol no existe"));

				Usuario usuarioId = usuarioRepo.findById(usuario.getId())
						.orElseThrow(() -> new ObjectNotFoundException("El id del usuario no existe"));

				Integer nUsuariosCedula = usuarioRepo.contarUsuariosPorCedula(usuario.getId(),
						usuario.getCedula());

				if (nUsuariosCedula == 0) {

					usuarioId.setCedula(usuario.getCedula());
					usuarioId.setNombre(usuario.getNombre());
					usuarioId.setApellido(usuario.getApellido());
					usuarioId.setClave(bcrypt.encode(usuario.getClave()));
					usuarioId.setRol(rolId);

					usuarioRepo.save(usuarioId);
				} else {
					throw new RegisteredObjectException("La cedula ingresada ya existe");
				}
			} else {
				throw new FieldRequiredException("Id de rol requerido en objeto rol");
			}
		} else {
			throw new FieldRequiredException("ID: Campo requerido");
		}
		
	}

	@Override
	public void eliminar(Integer id) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Usuario> listar(Integer nPagina, Integer cantidad, String orden)
			throws ListNoContentException, ParameterInvalidException {
		
		if (orden.equals("ASC") || orden.equals("DESC")) {
			if (nPagina > -1 && cantidad > 0) {
				PageRequest pageRequest;
				if (orden.equals("ASC")) {
					pageRequest = PageRequest.of(nPagina, cantidad, Sort.by("nombre").ascending());
				} else {
					pageRequest = PageRequest.of(nPagina, cantidad, Sort.by("nombre").descending());
				}
				Page<Usuario> page = usuarioRepo.findAll(pageRequest);
				if (page.getContent().size() > 0) {
					for (Usuario usuario : page.getContent()) {
						usuario.setRol(null);
						usuario.setClave(null);
					}

					return page;
				} else {
					throw new ListNoContentException();
				}
			} else if (nPagina < 0 && cantidad > 0) {
				throw new ParameterInvalidException("El número de página debe ser mínimo 0");
			} else if (nPagina > -1 && cantidad < 1) {
				throw new ParameterInvalidException("La cantidad de datos de página debe ser mínimo 1");
			} else {
				throw new ParameterInvalidException(
						"El número de página debe ser minimo 0 y la cantidad de datos debe ser mínimo 1");
			}
		} else {
			throw new ParameterInvalidException("Parámetro orden inválido, debe ser 'ASC' o 'DESC'");
		}
	}

	@Override
	public Usuario obtenerPorId(Integer id) throws ObjectNotFoundException {
		Usuario usuario = usuarioRepo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("El id del usuario no existe"));
		usuario.setRol(null);
		usuario.setClave(null);
		
		return usuario;
	}

	/*
	 * Iniciar sesion
	 */
	@Override
	public UserDetails loadUserByUsername(String cedula) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findOneByCedula(cedula);
		
        /*
		if (usuario == null) {
			throw new LoginException("Usuario no encontrado");
		}*/
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
		
		UserDetails ud = new User(usuario.getCedula(), usuario.getClave(), roles);
		
		return ud;
	}
    
    

}
