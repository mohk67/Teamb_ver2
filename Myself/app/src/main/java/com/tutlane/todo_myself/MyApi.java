package com.tutlane.todo_myself;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MyApi {
//    static final String BASE_URL = "http://royalkeep-v2.eu-4.evennode.com/api/main/";

    @Headers("Content-Type: application/json")
    @POST("login")
    Call<User> getUser(@Body JsonObject jsonObject);

    @Headers("Username: parto")
    @GET("labels")
    Call<List<Label>> getLabels();

    @GET("posts")
    Call<List<Post>> getPosts();


}
