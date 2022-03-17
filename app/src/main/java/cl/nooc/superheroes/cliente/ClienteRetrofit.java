package cl.nooc.superheroes.cliente;

import cl.nooc.superheroes.servicio.SuperServicio;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteRetrofit {

    private static Retrofit instance;
    public final static String BASE_URL = "https://akabab.github.io/superhero-api/api/";

    private ClienteRetrofit() {}

    public static SuperServicio getInstance(String url)
    {
        if(instance == null)
        {
            instance = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance.create(SuperServicio.class);
    }
}
