package com.example.mymovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymovie.API.ApiClient;
import com.example.mymovie.API.MovieService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE_ID = "movie_ID";
    public static final int  DEFAULT_ID = -1;
    Moviedetail moviedetail;
    ImageView Banner,imagePoster;
    TextView txtTitle,txtVote,labelVote,txtOverview, txtReleasedate,txtLanguge;
    ChipGroup chipGroup;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    CastAdapter castAdapter;
    ReviewAdapter reviewAdapter;
    RecyclerView RVCast;
    RecyclerView RvReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeLight);
        setContentView(R.layout.activity_movie_detail);
        final int MovieID= getIntent().getIntExtra(EXTRA_MOVIE_ID,DEFAULT_ID);
        if(MovieID==DEFAULT_ID){
            return;
        }
        initviews();
        getdata(MovieID);
   }
     private void initviews(){
         Banner=findViewById(R.id.banner);
         imagePoster=findViewById(R.id.image_poster);
         txtTitle=findViewById(R.id.txt_title);
         txtReleasedate=findViewById(R.id.txt_releasedate);
         txtLanguge=findViewById(R.id.txt_languge);
         txtOverview=findViewById(R.id.txt_overview);
         txtVote=findViewById(R.id.txt_vote);
         labelVote=findViewById(R.id.lable_vote);
         chipGroup=findViewById(R.id.group_chip);
         appBarLayout=findViewById(R.id.Appbar);
        moviedetail =new Moviedetail();
        collapsingToolbarLayout=findViewById(R.id.collapsing);
        RVCast =findViewById(R.id.rv_cast);
        RvReview =findViewById(R.id.rv_review);
     }
     private void getdata(int MovieID){
         ApiClient.getInstance().getMovieDetail(MovieID,"7c513cbab31f5add1ba5eb93d2e54d70").enqueue(new Callback<Moviedetail>() {
             @Override
             public void onResponse(Call<Moviedetail> call, Response<Moviedetail> response) {
                 if (response.isSuccessful()){
                     moviedetail= response.body();
                     setdataToView();
                     setCastAdapter();
                     setReviewAdapter();
                 }
             }

             @Override
             public void onFailure(Call<Moviedetail> call, Throwable t) {
             }
         });
     }
     private void setdataToView(){
         String banner_url = MovieService.IMAGE_BASE_URL+ MovieService.IMAGE_SIZE_BANNER+
                 moviedetail.getBackdropPath();
         Picasso.get().load(banner_url).into(Banner);
         String poster_url = MovieService.IMAGE_BASE_URL+ MovieService.IMAGE_SIZE_SMALL+
                 moviedetail.getPosterPath();
         Picasso.get().load(poster_url).into(imagePoster);
         txtTitle.setText(moviedetail.getTitle());
         for(Genre genre:
         moviedetail.getGenres()){
             Chip chip =new Chip(this);
             chip.setText(genre.getName());
             chip.setChipStrokeWidth(3);
             chip.setChipStrokeColor(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.colorPrimaryDark)));
             chip.setChipBackgroundColorResource(android.R.color.darker_gray);
             chipGroup.addView(chip);
     }
         txtReleasedate.setText(moviedetail.getReleaseDate());
         txtVote.setText(String.valueOf(moviedetail.getVoteAverage()));
         txtOverview.setText(moviedetail.getOverview());
         txtLanguge.setText(moviedetail.getOriginalLanguage());
         labelVote.setText(String.valueOf(moviedetail.getVoteCount()));
     }
     private void setCastAdapter(){
        castAdapter = new CastAdapter(moviedetail.getCredits().getCast());
        RVCast.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        RVCast.setAdapter(castAdapter);

    }
    private void setReviewAdapter(){
        reviewAdapter = new ReviewAdapter(moviedetail.getReviews().getResults());
        RvReview.setLayoutManager(new LinearLayoutManager(this));
        RvReview.setAdapter(reviewAdapter);

    }
}