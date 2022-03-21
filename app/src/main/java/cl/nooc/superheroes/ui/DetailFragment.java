package cl.nooc.superheroes.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import cl.nooc.superheroes.R;
import cl.nooc.superheroes.databinding.FragmentDetailBinding;
import cl.nooc.superheroes.modelo.SuperRespuestaItem;
import cl.nooc.superheroes.viewmodel.HeroViewModel;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private HeroViewModel viewModel;
    private boolean img = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);

        binding.setLifecycleOwner(getViewLifecycleOwner());

        viewModel = new ViewModelProvider(getActivity()).get(HeroViewModel.class);

        viewModel.getDetalle().observe(getViewLifecycleOwner(), superRespuestaItem -> {
            binding.setDetalle(superRespuestaItem);

            Picasso.get().load(superRespuestaItem.getImages().getSm()).into(binding.ivDetail);
        });

        binding.ivFav.setOnClickListener(v -> {
            if(img){
            img = false;
            binding.ivFav.setImageResource(R.drawable.star_fav);
            } else{
            img = true;
            binding.ivFav.setImageResource(R.drawable.star_fav_add);
            }

        });

        return binding.getRoot();
    }

}