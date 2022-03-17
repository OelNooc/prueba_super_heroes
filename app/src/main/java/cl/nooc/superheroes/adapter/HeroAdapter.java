package cl.nooc.superheroes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cl.nooc.superheroes.R;
import cl.nooc.superheroes.databinding.ItemLayoutBinding;
import cl.nooc.superheroes.modelo.SuperRespuestaItem;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.CustomViewHolder> {

    private List<SuperRespuestaItem> lista = new ArrayList<>();
    private MiOnClickListener listener;

    public void setLista(List<SuperRespuestaItem> lista) {
        this.lista = lista;
        notifyDataSetChanged();
    }

    public void setListener(MiOnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.bindData(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        private ItemLayoutBinding binding;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemLayoutBinding.bind(itemView);
        }

        public void bindData(SuperRespuestaItem superRespuestaItem){

            Picasso.get().load(superRespuestaItem.getImages().getSm()).into(binding.ivImagen);

            itemView.setOnClickListener(v -> {
                listener.onClickListener(superRespuestaItem);
            });
        }
    }

    public interface MiOnClickListener{
        void onClickListener(SuperRespuestaItem superRespuestaItem);
    }
}
