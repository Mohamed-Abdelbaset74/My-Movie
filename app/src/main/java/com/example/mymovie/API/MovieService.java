package com.example.mymovie.API;

import com.example.mymovie.Moviedetail;
import com.example.mymovie.model.Movieresponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
    String IMAGE_BASE_URL="https://image.tmdb.org/t/p";
    String IMAGE_SIZE_SMALL="/w185";
    String IMAGE_SIZE_BANNER="/w780";


@GET("movie/popular")
Call<Movieresponse> getPopularMovies(@Query("api_key") String apiKey);
@GET("movie/{movie_id}?append_to_response=videos,credits,reviews")
Call<Moviedetail> getMovieDetail(@Path("movie_id") int id , @Query("api_key") String apiKey);
}
