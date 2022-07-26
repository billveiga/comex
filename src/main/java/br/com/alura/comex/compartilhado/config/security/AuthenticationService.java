package br.com.alura.comex.compartilhado.config.security;

import br.com.alura.comex.compartilhado.infra.usuario.UsuarioEntity;
import br.com.alura.comex.compartilhado.infra.usuario.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UsuarioEntity> usuario = usuarioDAO.findByEmail(username);

        if (usuario.isPresent()) return usuario.get();

        throw new UsernameNotFoundException("Email inválido!");
    }
}
