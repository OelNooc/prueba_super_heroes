package cl.nooc.superheroes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cl.nooc.superheroes.databinding.ActivityMainBinding;
import cl.nooc.superheroes.viewmodel.HeroViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MediaPlayer mp;
    private HeroViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(HeroViewModel.class);
        viewModel.llamarApi();
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d(viewModel.getRespuesta().toString());

        mp = MediaPlayer.create(this, R.raw.marvel_intro);
        mp.setLooping(true);
        mp.start();
    }
}