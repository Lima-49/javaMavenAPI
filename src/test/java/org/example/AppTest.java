package org.example;

import org.junit.jupiter.api.BeforeEach;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @Test
    public void callApiTesteSucesso() {
        app = new App();
        app.setDominio("facens.com.br");
        JsonObject apiResponse = app.callApi();
        assertTrue(apiResponse.has("domain"));
    }

    @Test
    public void callApiTesteErro() {
        app = new App();
        app.setDominio("");
        JsonObject apiResponse = app.callApi();
        assertTrue(apiResponse.has("status_code"));
    }

    @Test
    public void testCallApiWithException() {
        app = new App();
        String urlOriginal = app.getApiURL();
        app.setApiURL("https://invalid-url/");
        app.setDominio("example.com");
        JsonObject response = app.callApi();
        assertEquals(500, response.get("status_code").getAsInt());
        app.setApiURL(urlOriginal);
    }
}