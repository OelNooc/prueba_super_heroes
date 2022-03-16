package cl.nooc.superheroes.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.nooc.superheroes.R;
import cl.nooc.superheroes.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        new Handler().postDelayed(()->{
            Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_listFragment);
        }, 6000);

        return binding.getRoot();
    }
}