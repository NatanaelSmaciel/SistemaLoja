package br.edu.poo.m0025.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import br.edu.poo.m0025.service.ProdutoAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ProdutoAPI {

    public void consultarProdutos() {

        try {

            HttpClient cliente = HttpClient.newHttpClient();

            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create("https://fakestoreapi.com/products"))
                    .GET()
                    .build();

            HttpResponse<String> resposta = cliente.send(
                    requisicao,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (resposta.statusCode() != 200) {
                System.out.println("Erro HTTP: " + resposta.statusCode());
                return;
            }

            JsonArray produtos = JsonParser.parseString(
                    resposta.body()).getAsJsonArray();

            System.out.println("\n===== PRODUTOS EXTERNOS =====");

            for (int i = 0; i < produtos.size(); i++) {

                JsonObject produto = produtos.get(i).getAsJsonObject();

                System.out.println("ID: " + produto.get("id").getAsInt());
                System.out.println("Nome: " + produto.get("title").getAsString());
                System.out.println("Preco (API): " + produto.get("price").getAsBigDecimal());
                System.out.println("Categoria: " + produto.get("category").getAsString());

                System.out.println("-----------------------------");
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar API: " + e.getMessage());
        }
    }
}
