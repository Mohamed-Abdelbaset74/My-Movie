package com.example.mymovie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovie.API.MovieService;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {
    private List<Cast> casts;

    public CastAdapter(List<Cast> casts) {
        this.casts = casts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cast,parent,false);
        return new CastAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String profileImage = MovieService.IMAGE_BASE_URL + MovieService.IMAGE_SIZE_SMALL +
                casts.get(position).getProfilePath();
        Picasso.get().load(profileImage).into(holder.imageCast);
        holder.txtCast.setText(casts.get(position).getCharacter());
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imageCast;
        TextView txtCast;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCast=itemView.findViewById(R.id.image_cast);
            txtCast=itemView.findViewById(R.id.txt_cast);
        }
    }
}
