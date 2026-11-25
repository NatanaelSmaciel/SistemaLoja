package br.edu.poo.m0025.model;

import java.math.BigDecimal;

public class Produto {

    // Atributos
    private int id;
    private String nome;
    private BigDecimal preco;
    private int estoque;
    private Categoria categoria;

    // Construtor vazio
    public Produto() {
    }

    // Construtor completo
    public Produto(int id, String nome, BigDecimal preco,
                   int estoque, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Exibir informações
    @Override
    public String toString() {
        return "Produto: " + nome +
                " | Preço: R$ " + preco +
                " | Estoque: " + estoque +
                " | Categoria: " +
                (categoria != null ? categoria.getNome() : "Não definida");
    }
}
