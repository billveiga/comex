package br.com.alura.comex.compartilhado.infra.usuario;


import br.com.alura.comex.compartilhado.entity.usuario.Usuario;
import br.com.alura.comex.compartilhado.infra.cliente.ClienteEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Entity @Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class UsuarioEntity implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String senha;

    @OneToOne( optional = false)
    private ClienteEntity cliente;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    public static UsuarioEntity converter(Usuario usuario){
        return UsuarioEntity.builder()
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .build();
    }

    public Usuario paraUsuario(){
        return Usuario.builder()
                .senha(this.senha)
                .email(this.senha)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}