package br.com.alura.comex.compartilhado.entity.usuario;

public interface UsuarioRepository {
    Usuario registrarNovoUsuario(Usuario usuario);

    Usuario buscarUsuario(String email);
}
