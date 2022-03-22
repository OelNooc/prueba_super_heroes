package cl.nooc.superheroes.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cl.nooc.superheroes.R;
import cl.nooc.superheroes.adapter.HeroAdapter;
import cl.nooc.superheroes.databinding.FragmentFavsBinding;
import cl.nooc.superheroes.modelo.SuperRespuestaItem;
import cl.nooc.superheroes.viewmodel.HeroViewModel;

public class FavsFragment extends Fragment {

    private FragmentFavsBinding binding;
    private HeroViewModel viewModel;
    private HeroAdapter adapter;
    private  List<SuperRespuestaItem> favoritos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavsBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(HeroViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new HeroAdapter();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.rvLista1.setAdapter(adapter);
        binding.rvLista1.setLayoutManager(manager);

        adapter.setListener(superRespuestaItem -> {
            viewModel.obtenerDetalle(superRespuestaItem);
            Bundle b = new Bundle();
            b.putSerializable("item", (Serializable) superRespuestaItem);
            Navigation.findNavController(getView()).navigate(R.id.action_favsFragment_to_detailFragment, b);
        });

        favoritos = (List<SuperRespuestaItem>) getArguments().getSerializable("lista");
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.i(favoritos.toString());

        viewModel.getRespuesta().observe(getViewLifecycleOwner(), superRespuestaItems -> {
            adapter.setLista(favoritos);
        });

        binding.btnFavs1.setEnabled(false);
        binding.btnFavs1.setBackgroundColor(Color.GRAY);

        binding.btnLista1.setOnClickListener(v -> {
            Navigation.findNavController(getView()).navigate(R.id.action_favsFragment_to_listFragment);
        });
    }

}