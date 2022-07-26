package br.com.alura.comex.compartilhado.entity.usuario;

import br.com.alura.comex.compartilhado.entity.cliente.Cliente;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder @Setter
public class Usuario {

    private String email;

    private String senha;

    private Cliente cliente;
    private List<Perfil> perfis = new ArrayList<>();

    public void adicionarPerfil(String nome){
        this.perfis.add(new Perfil(nome));
    }


}
