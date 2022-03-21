package cl.nooc.superheroes.cliente;

import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.common.truth.Truth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import cl.nooc.superheroes.LectorJson;
import cl.nooc.superheroes.modelo.SuperRespuestaItem;
import cl.nooc.superheroes.servicio.SuperServicio;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

@RunWith(AndroidJUnit4.class)
public class ClienteRetrofitTest {

    private MockWebServer server;
    private String url = "http://localhost:8080/api/";
    private String body = LectorJson.lector("SuperApiTest.json");

    @Before
    public void setUp() throws Exception {

        server = new MockWebServer();
        server.start(8080);
        server.enqueue(new MockResponse().setResponseCode(200).setBody(body));
        server.url("all.json");

    }

    @After
    public void tearDown() throws Exception {

        server.shutdown();

    }

    @Test
    public void getInstanceTest() {

        SuperServicio servicio = ClienteRetrofit.getInstance(url);
        try{
            List<SuperRespuestaItem> lista = servicio.getSupers().execute().body();
            Truth.assertThat(lista.size()).isEqualTo(3);
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}