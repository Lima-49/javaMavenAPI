package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App {
    private String dominio;
    private String apiURL = "https://app.valimail.com/domain_checker/v1/status/";

    public String getApiURL() {
        return apiURL;
    }

    public void setApiURL(String apiURL) {
        this.apiURL = apiURL;
    }


    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public JsonObject callApi() {

        try {
            String apiUrl = apiURL + dominio + ".json";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Converte a resposta JSON em um objeto JsonObject usando Gson
                JsonElement jsonElement = JsonParser.parseString(response.body());
                return jsonElement.getAsJsonObject();

            } else {
                // Se a resposta não for 200, retorna um objeto JSON com status code e mensagem de erro
                JsonObject errorObject = new JsonObject();
                errorObject.addProperty("status_code", response.statusCode());
                errorObject.addProperty("msg_error", "Erro ao realizar a chamada da API");
                return errorObject;
            }
        } catch (IOException | InterruptedException e) {
            // Em caso de exceção, retorna um objeto JSON com status code 500 e mensagem de erro
            JsonObject errorObject = new JsonObject();
            errorObject.addProperty("status_code", 500);
            errorObject.addProperty("msg_error", "Erro ao realizar a chamada da API: " + e.getMessage());
            return errorObject;
        }
    }

}
