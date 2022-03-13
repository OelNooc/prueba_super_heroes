package cl.nooc.superheroes.cliente;

import cl.nooc.superheroes.servicio.SuperServicio;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteRetrofit {

    private static Retrofit instance;

    private ClienteRetrofit() {}

    public static SuperServicio getInstance()
    {
        if(instance == null)
        {
            instance = new Retrofit.Builder()
                    .baseUrl("https://akabab.github.io/superhero-api/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance.create(SuperServicio.class);
    }
}
