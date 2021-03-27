package com.example.mymovie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovie.API.MovieService;
import com.example.mymovie.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
   private List<com.example.mymovie.model.Result> movielist;
    private onClick click;

    public MovieAdapter(List<Result> movielist, onClick click) {
        this.movielist = movielist;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Result result = movielist.get(position);
        holder.txtTitle.setText(result.getTitle());
        String image_url = MovieService.IMAGE_BASE_URL+ MovieService.IMAGE_SIZE_SMALL+
                result.getPosterPath();
        Picasso.get().load(image_url).into(holder.imgeMovie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.MovieItemSelected(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movielist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgeMovie;
        TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgeMovie=itemView.findViewById(R.id.image_movie);
            txtTitle=itemView.findViewById(R.id.txt_title);

        }
    }

    public interface onClick{
        void MovieItemSelected(int position);
    }
}
