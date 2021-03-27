package com.example.mymovie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.mymovie.API.MovieService;
import com.example.mymovie.model.Result;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<ReviewResult> reviews;

    public ReviewAdapter(List<ReviewResult> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review,parent,false);
        return new ReviewAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewResult review = reviews.get(position);
        String username =review.getAuthor();
        holder.txtAuthor.setText(username);
        holder.txtContent.setText(review.getContent());
        // color of half image
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder().buildRound(username.substring(0,1).toUpperCase(),color);
        holder.imageAuthor.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        if(reviews!=null){
            return reviews.size();
        }else
            {
                return 0;
            }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageAuthor;
        TextView txtAuthor,txtContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAuthor=itemView.findViewById(R.id.image_author);
            txtAuthor=itemView.findViewById(R.id.txt_author);
            txtContent=itemView.findViewById(R.id.txt_content);
        }
    }
}
