
package br.edu.poo.m0025.dao;

import br.edu.poo.m0025.database.Conexao;
import br.edu.poo.m0025.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoriaDAO {

    public boolean cadastrar(Categoria categoria) {

        if (!validar(categoria)) {
            return false;
        }

        String sql = "INSERT INTO categorias (nome) VALUES (?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());

            stmt.executeUpdate();

            System.out.println("Categoria cadastrada!");

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao cadastrar: " + e.getMessage());

            return false;
        }
    }


    public void listar() {

        String sql = "SELECT * FROM categorias";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             java.sql.ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                Categoria categoria = new Categoria();

                categoria.setId(resultado.getInt("id"));
                categoria.setNome(resultado.getString("nome"));

                System.out.println("ID: " + categoria.getId()
                        + " | Nome: " + categoria.getNome());
            }

        } catch (SQLException e) {

            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }


    public boolean atualizar(Categoria categoria) {

        String sql = "UPDATE categorias SET nome = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas > 0) {
                System.out.println("Categoria atualizada!");
                return true;
            } else {
                System.out.println("Categoria não encontrada!");
                return false;
            }

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar: " + e.getMessage());
            return false;
        }
    }


    public boolean excluir(int id) {

        String sql = "DELETE FROM categorias WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasExcluidas = stmt.executeUpdate();

            if (linhasExcluidas > 0) {
                System.out.println("Categoria excluída!");
                return true;
            } else {
                System.out.println("Categoria não encontrada!");
                return false;
            }

        } catch (SQLException e) {

            System.out.println("Erro ao excluir: " + e.getMessage());
            return false;
        }
    }

    private boolean validar(Categoria categoria) {

        if (categoria.getNome() == null || categoria.getNome().isBlank()) {
            System.out.println("O nome da categoria nao pode ser vazio!");
            return false;
        }

        return true;
    }



}
