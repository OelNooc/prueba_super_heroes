package cl.nooc.superheroes.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

import cl.nooc.superheroes.cliente.ClienteRetrofit;
import cl.nooc.superheroes.modelo.SuperRespuesta;
import cl.nooc.superheroes.modelo.SuperRespuestaItem;
import cl.nooc.superheroes.servicio.SuperServicio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroViewModel extends ViewModel {

    private SuperServicio servicio = ClienteRetrofit.getInstance(ClienteRetrofit.BASE_URL);

    private MutableLiveData<List<SuperRespuestaItem>> lista = new MutableLiveData<>();
    private MutableLiveData<SuperRespuestaItem> detalle = new MutableLiveData<>();

    public void llamarApi() {
        servicio.getSupers().enqueue(new Callback<List<SuperRespuestaItem>>() {
            @Override
            public void onResponse(Call<List<SuperRespuestaItem>> call, Response<List<SuperRespuestaItem>> response) {
                lista.postValue(response.body());
                Logger.addLogAdapter(new AndroidLogAdapter());
                Logger.d(response.body().toString());
            }

            @Override
            public void onFailure(Call<List<SuperRespuestaItem>> call, Throwable t) {
                Logger.addLogAdapter(new AndroidLogAdapter());
                Logger.d(t.getMessage());
                call.cancel();
            }
        });
    }

    public void obtenerDetalle(SuperRespuestaItem superRespuestaItem) {
        detalle.setValue(superRespuestaItem);
    }

    public MutableLiveData<List<SuperRespuestaItem>> getRespuesta() {
        return lista;
    }

    public MutableLiveData<SuperRespuestaItem> getDetalle() {
        return detalle;
    }
}
