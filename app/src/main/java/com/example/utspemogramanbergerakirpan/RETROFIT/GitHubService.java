package com.example.utspemogramanbergerakirpan.RETROFIT;

import com.example.utspemogramanbergerakirpan.RESPONSE.ResponseGithub;
import com.example.utspemogramanbergerakirpan.RESPONSE.ResponseUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {
    @GET("search/users")
    Call<ResponseUser> getUsers(@Query("q") String query);


    @GET("users/{username}")
    Call<ResponseGithub> getGithub(@Path("username") String username);
}

