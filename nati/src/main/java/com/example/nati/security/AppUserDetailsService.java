package com.example.nati.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.nati.model.Usuario;
import com.example.nati.repository.UsuarioRepository;

public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepsository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Optional<Usuario> usuarioOptional = usuarioRepsository.findByEmail(email);
//		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario e/ou senha incorretos"));
//		return new User(email, usuario.getSenha(), getPermissoes(usuario));
		return null;
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
//		Set<SimpleGrantedAuthority> authorites = new HashSet<>();
//		usuario.getPermissoes().forEach(p -> authorites.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
//		return authorites;
		return null;
	}

}
