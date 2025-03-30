package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import model.Adress;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SearchCep {

    public Adress findAdress(String cep) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("Não localizamos o endereço no cep informado");
        }

        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            Adress newAdress = gson.fromJson(response.body(), Adress.class);

            String jsonFormated = gson.toJson(newAdress);
            System.out.println(jsonFormated);

            return newAdress;
        } catch (JsonSyntaxException e) {
            throw new IOException("Cep inválido! Verifique se digitou corretamente");
        }

    }
}
