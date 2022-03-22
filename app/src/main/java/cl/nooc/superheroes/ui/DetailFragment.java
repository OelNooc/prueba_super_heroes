package cl.nooc.superheroes.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import cl.nooc.superheroes.R;
import cl.nooc.superheroes.databinding.FragmentDetailBinding;
import cl.nooc.superheroes.modelo.SuperRespuestaItem;
import cl.nooc.superheroes.viewmodel.HeroViewModel;
import io.github.muddz.styleabletoast.StyleableToast;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private HeroViewModel viewModel;
    private boolean img = false;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);

        binding.setLifecycleOwner(getViewLifecycleOwner());

        viewModel = new ViewModelProvider(getActivity()).get(HeroViewModel.class);

        db = FirebaseFirestore.getInstance();

        viewModel.getDetalle().observe(getViewLifecycleOwner(), superRespuestaItem -> {
            binding.setDetalle(superRespuestaItem);

            Picasso.get().load(superRespuestaItem.getImages().getSm()).into(binding.ivDetail);
        });

        binding.ivFav.setOnClickListener(v -> {
            if(img){
            img = false;
            binding.ivFav.setImageResource(R.drawable.star_fav);
            guardarSuper();
            } else{
            img = true;
            binding.ivFav.setImageResource(R.drawable.star_fav_add);
            }

        });

        return binding.getRoot();
    }

    private void guardarSuper(){
        SuperRespuestaItem item = (SuperRespuestaItem) getArguments().getSerializable("item");
        CollectionReference dbSuper = db.collection("supers");
        dbSuper.add(item).addOnSuccessListener(documentReference -> {
            StyleableToast.makeText(getContext(),"Super agregado a Favoritos", Toast.LENGTH_LONG,
                    R.style.mytoast).show();
        }).addOnFailureListener(e -> {
            StyleableToast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }

}