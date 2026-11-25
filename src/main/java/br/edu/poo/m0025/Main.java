package br.edu.poo.m0025;

import br.edu.poo.m0025.dao.CategoriaDAO;
import br.edu.poo.m0025.dao.ProdutoDAO;
import java.util.Scanner;
import br.edu.poo.m0025.model.Categoria;
import br.edu.poo.m0025.model.Produto;
import br.edu.poo.m0025.service.ProdutoAPI;

import java.math.BigDecimal;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ProdutoAPI produtoAPI = new ProdutoAPI();

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== SISTEMA LOJA =====");

            System.out.println("1 - Cadastrar categoria");
            System.out.println("2 - Listar categorias");
            System.out.println("3 - Atualizar categoria");
            System.out.println("4 - Excluir categoria");

            System.out.println("5 - Cadastrar produto");
            System.out.println("6 - Listar produtos");
            System.out.println("7 - Atualizar produto");
            System.out.println("8 - Excluir produto");
            System.out.println("9 - Consultar produtos externos");

            System.out.println("0 - Sair");

            System.out.print("Escolha uma opcao: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    Categoria categoria = new Categoria();

                    System.out.print("Digite o nome da categoria: ");
                    categoria.setNome(scanner.nextLine());

                    categoriaDAO.cadastrar(categoria);
                    break;

                case 2:
                    categoriaDAO.listar();
                    break;

                case 3:
                    Categoria categoriaAtualizada = new Categoria();

                    System.out.print("Digite o ID da categoria: ");
                    categoriaAtualizada.setId(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Digite o novo nome: ");
                    categoriaAtualizada.setNome(scanner.nextLine());

                    categoriaDAO.atualizar(categoriaAtualizada);
                    break;

                case 4:
                    System.out.print("Digite o ID da categoria: ");
                    int idCategoria = scanner.nextInt();
                    scanner.nextLine();

                    categoriaDAO.excluir(idCategoria);
                    break;

                case 5:
                    Produto produto = new Produto();
                    Categoria categoriaProduto = new Categoria();

                    System.out.print("Digite o nome do produto: ");
                    produto.setNome(scanner.nextLine());

                    System.out.print("Digite o preco: ");
                    produto.setPreco(new BigDecimal(scanner.nextLine()));

                    System.out.print("Digite a quantidade em estoque: ");
                    produto.setEstoque(scanner.nextInt());
                    scanner.nextLine();

                    categoriaDAO.listar();

                    System.out.print("Digite o ID da categoria: ");
                    categoriaProduto.setId(scanner.nextInt());
                    scanner.nextLine();

                    produto.setCategoria(categoriaProduto);

                    produtoDAO.cadastrar(produto);
                    break;

                case 6:
                    produtoDAO.listar();
                    break;

                case 7:
                    Produto produtoAtualizado = new Produto();
                    Categoria novaCategoria = new Categoria();

                    produtoDAO.listar();

                    System.out.print("Digite o ID do produto: ");
                    produtoAtualizado.setId(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Digite o novo nome: ");
                    produtoAtualizado.setNome(scanner.nextLine());

                    System.out.print("Digite o novo preco: ");
                    produtoAtualizado.setPreco(new BigDecimal(scanner.nextLine()));

                    System.out.print("Digite o novo estoque: ");
                    produtoAtualizado.setEstoque(scanner.nextInt());
                    scanner.nextLine();

                    categoriaDAO.listar();

                    System.out.print("Digite o ID da categoria: ");
                    novaCategoria.setId(scanner.nextInt());
                    scanner.nextLine();

                    produtoAtualizado.setCategoria(novaCategoria);

                    produtoDAO.atualizar(produtoAtualizado);
                    break;

                case 8:
                    produtoDAO.listar();

                    System.out.print("Digite o ID do produto: ");
                    int idProduto = scanner.nextInt();
                    scanner.nextLine();

                    produtoDAO.excluir(idProduto);
                    break;

                case 9:
                    produtoAPI.consultarProdutos();
                    break;

                case 0:
                    System.out.println("Sistema encerrado!");
                    break;

                default:
                    System.out.println("Opcao ainda nao implementada!");
            }
        }

        scanner.close();
    }
}