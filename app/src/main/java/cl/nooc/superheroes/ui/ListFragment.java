package cl.nooc.superheroes.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButtonToggleGroup;

import java.io.Serializable;

import cl.nooc.superheroes.R;
import cl.nooc.superheroes.adapter.HeroAdapter;
import cl.nooc.superheroes.databinding.FragmentListBinding;
import cl.nooc.superheroes.modelo.SuperRespuestaItem;
import cl.nooc.superheroes.viewmodel.HeroViewModel;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    private HeroViewModel viewModel;
    private HeroAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(HeroViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new HeroAdapter();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.rvLista.setAdapter(adapter);
        binding.rvLista.setLayoutManager(manager);

        adapter.setListener(superRespuestaItem -> {
            viewModel.obtenerDetalle(superRespuestaItem);
            Navigation.findNavController(getView()).navigate(R.id.action_listFragment_to_detailFragment);
        });

        viewModel.getRespuesta().observe(getViewLifecycleOwner(), superRespuestaItems -> {
            adapter.setLista(superRespuestaItems);
        });

        binding.btnLista.setOnClickListener(v -> {
            binding.btnLista.setEnabled(false);
            binding.btnFavs.setEnabled(true);
        });

        binding.btnFavs.setOnClickListener(v -> {
            binding.btnFavs.setEnabled(false);
            binding.btnLista.setEnabled(true);
        });
    }
}