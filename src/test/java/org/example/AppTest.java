package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    App app;

    @Test
    public void callApiTesteCorreto() {
        app = new App();
        app.setDominio("facens.com.br");
        app.callApi();
    }

    @Test
    public void callApiTesteIncorreto() {
        app = new App();
        app.setDominio("facens");
        app.callApi();
    }

}