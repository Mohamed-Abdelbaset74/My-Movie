package com.example.mymovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.mymovie.API.ApiClient;
import com.example.mymovie.model.Movieresponse;
import com.example.mymovie.model.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieAdapter.onClick {
    List<Result>movies;
    MovieAdapter Adapter;
    RecyclerView RV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Inializedata();
        getData();
    }


    private void Inializedata(){
        RV=findViewById(R.id.rv_main);
        movies =new ArrayList<>();
        RV.setLayoutManager(new GridLayoutManager(this,3));
        Adapter = new MovieAdapter(movies,this);
        RV.setAdapter(Adapter);


    }

    private void getData(){

        ApiClient.getInstance().getPopularMovies("7c513cbab31f5add1ba5eb93d2e54d70").enqueue(new Callback<Movieresponse>() {
            @Override
            public void onResponse(Call<Movieresponse> call, Response<Movieresponse> response) {
                if(response.isSuccessful()){
                    movies.clear();
                    movies.addAll(response.body().getResults());
                    Adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Movieresponse> call, Throwable t) {

                Log.e("Error", "onFailure: ", t);

            }
        });
    }

    @Override
    public void MovieItemSelected(int position) {
        Intent intent = new Intent(this,MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID,movies.get(position).getId());
        startActivity(intent);

    }
}