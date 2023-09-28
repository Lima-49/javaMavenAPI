package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class App 
{
    private String dominio;

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public void callApi() {

        try {
            String apiUrl = "https://app.valimail.com/domain_checker/v1/status/" + dominio + ".json";

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();

                // Imprima a resposta da API
                System.out.println("Resposta da API: " + response);
            } else {
                System.out.println("Erro na chamada da API. Código de resposta: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
