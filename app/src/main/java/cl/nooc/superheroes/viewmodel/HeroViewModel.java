package cl.nooc.superheroes.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cl.nooc.superheroes.cliente.ClienteRetrofit;
import cl.nooc.superheroes.modelo.SuperRespuesta;
import cl.nooc.superheroes.modelo.SuperRespuestaItem;
import cl.nooc.superheroes.servicio.SuperServicio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroViewModel extends AndroidViewModel {

    private SuperServicio servicio = ClienteRetrofit.getInstance(ClienteRetrofit.BASE_URL);
    private MutableLiveData<SuperRespuesta> respuesta = new MutableLiveData<>();
    private MutableLiveData<SuperRespuestaItem> detalle = new MutableLiveData<>();

    public HeroViewModel(@NonNull Application application) {
        super(application);
    }

    public void llamarApi(){
        servicio.getSupers().enqueue(new Callback<SuperRespuesta>() {
            @Override
            public void onResponse(Call<SuperRespuesta> call, Response<SuperRespuesta> response) {
                respuesta.postValue(response.body());
            }

            @Override
            public void onFailure(Call<SuperRespuesta> call, Throwable t) {
                Logger.addLogAdapter(new AndroidLogAdapter());
                Logger.i("API", t.getMessage());
                call.cancel();
            }
        });
    }

    public void obtenerDetalle(SuperRespuestaItem superRespuestaItem) {
        detalle.setValue(superRespuestaItem);
    }

    public MutableLiveData<SuperRespuesta> getRespuesta() {
        return respuesta;
    }

    public MutableLiveData<SuperRespuestaItem> getDetalle() {
        return detalle;
    }
}
