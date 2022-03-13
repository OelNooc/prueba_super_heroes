package cl.nooc.superheroes.servicio;

import cl.nooc.superheroes.modelo.SuperRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SuperServicio {

    @GET("all.json")
    public Call<SuperRespuesta> getSupers();
}
