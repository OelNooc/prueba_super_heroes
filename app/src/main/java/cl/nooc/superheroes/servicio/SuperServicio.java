package cl.nooc.superheroes.servicio;

import java.util.List;

import cl.nooc.superheroes.modelo.SuperRespuesta;
import cl.nooc.superheroes.modelo.SuperRespuestaItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SuperServicio {

    @GET("all.json")
    public Call<List<SuperRespuestaItem>> getSupers();

}
