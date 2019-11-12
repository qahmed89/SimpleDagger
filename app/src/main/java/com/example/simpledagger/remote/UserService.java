package com.example.simpledagger.remote;

import com.example.simpledagger.model.details.Details;
import com.example.simpledagger.model.movies.Response;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    @GET("list_movies.json")
    Single<Response> getDb();
    @GET("movie_details.json?movie_id=")
    Single<Details> getDetails(@Query("movie_id") int Id);

}
