package cl.nooc.superheroes.ui;

import android.graphics.Color;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
            Bundle b = new Bundle();
            b.putSerializable("item", (Serializable) superRespuestaItem);
            Navigation.findNavController(getView()).navigate(R.id.action_listFragment_to_detailFragment, b);
        });

        viewModel.getRespuesta().observe(getViewLifecycleOwner(), superRespuestaItems -> {
            adapter.setLista(superRespuestaItems);
        });

        binding.btnLista.setEnabled(false);
        binding.btnLista.setBackgroundColor(Color.GRAY);

        binding.btnFavs.setOnClickListener(v -> {
            Navigation.findNavController(getView()).navigate(R.id.action_listFragment_to_favsFragment);
        });
    }

}