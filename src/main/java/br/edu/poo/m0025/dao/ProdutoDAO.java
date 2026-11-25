
package br.edu.poo.m0025.dao;

import br.edu.poo.m0025.database.Conexao;
import br.edu.poo.m0025.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;

import br.edu.poo.m0025.model.Categoria;

public class ProdutoDAO {

    public boolean cadastrar(Produto produto) {

        if (!validar(produto)) {
            return false;
        }

        String sql = "INSERT INTO produtos (nome, preco, estoque, categoria_id) VALUES (?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setBigDecimal(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());
            stmt.setInt(4, produto.getCategoria().getId());

            stmt.executeUpdate();

            System.out.println("Produto cadastrado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            return false;
        }
    }

    public void listar() {

        String sql = "SELECT p.*, c.nome AS categoria_nome " +
                "FROM produtos p " +
                "INNER JOIN categorias c ON p.categoria_id = c.id";


        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                Produto produto = new Produto();
                Categoria categoria = new Categoria();

                produto.setId(resultado.getInt("id"));
                produto.setNome(resultado.getString("nome"));
                produto.setPreco(resultado.getBigDecimal("preco"));
                produto.setEstoque(resultado.getInt("estoque"));

                categoria.setId(resultado.getInt("categoria_id"));
                categoria.setNome(resultado.getString("categoria_nome"));
                produto.setCategoria(categoria);

                System.out.println("ID: " + produto.getId() + " | " + produto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }

    public boolean atualizar(Produto produto) {

        if (!validar(produto)) {
            return false;
        }

        String sql = "UPDATE produtos SET nome = ?, preco = ?, estoque = ?, categoria_id = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setBigDecimal(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());
            stmt.setInt(4, produto.getCategoria().getId());
            stmt.setInt(5, produto.getId());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Produto atualizado com sucesso!");
                return true;
            }

            System.out.println("Produto nao encontrado!");
            return false;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {

        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Produto excluido com sucesso!");
                return true;
            }

            System.out.println("Produto nao encontrado!");
            return false;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
            return false;
        }
    }

    private boolean validar(Produto produto) {

        if (produto.getNome() == null || produto.getNome().isBlank()) {
            System.out.println("O nome nao pode ser vazio!");
            return false;
        }

        if (produto.getPreco() == null || produto.getPreco().signum() <= 0) {
            System.out.println("O preco deve ser maior que zero!");
            return false;
        }

        if (produto.getEstoque() < 0) {
            System.out.println("O estoque nao pode ser negativo!");
            return false;
        }

        if (produto.getCategoria() == null || produto.getCategoria().getId() <= 0) {
            System.out.println("Categoria invalida!");
            return false;
        }

        return true;
    }


}


