package br.com.alura.comex.compartilhado.adapter.controller.categoria.dto;

import br.com.alura.comex.compartilhado.entity.categoria.Categoria;
import br.com.alura.comex.compartilhado.entity.enuns.StatusCategoria;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoque;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoriaDto {

    private Long id;
    private String nome;
    private StatusCategoria status;

    private List<ProdutoEstoque> produtoEstoqueEntities;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
        this.produtoEstoqueEntities = categoria.getProdutoEstoques();
    }

    public static List<CategoriaDto> converter(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }
}
