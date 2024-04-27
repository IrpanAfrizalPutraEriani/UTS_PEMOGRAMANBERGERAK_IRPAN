package com.example.utspemogramanbergerakirpan.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utspemogramanbergerakirpan.R;
import com.example.utspemogramanbergerakirpan.RESPONSE.ResponseGithub;
import com.example.utspemogramanbergerakirpan.RETROFIT.GitHubService;
import com.example.utspemogramanbergerakirpan.RETROFIT.GithubConfig;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progressBar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String username = extras.getString("username");
            GitHubService Service = GithubConfig.getServices();
            Call<ResponseGithub> userCall = Service.getGithub(username);

            TextView Nama = findViewById(R.id.TeksNama);
            TextView Username = findViewById(R.id.TeksUsername);
            TextView Id = findViewById(R.id.Teksid);
            TextView Bio = findViewById(R.id.TeksBio);
            TextView Lokasi = findViewById(R.id.TeksLokasi);
            TextView Email = findViewById(R.id.TeksEmail);
            ImageView imageView = findViewById(R.id.Gambar);
            TextView Repo = findViewById(R.id.TeksRepos);
            TextView Gists = findViewById(R.id.TeksGists);
            TextView Followers = findViewById(R.id.Teksfollowers);
            TextView Following = findViewById(R.id.Teksfollowing);

            showLoading(true);
            userCall.enqueue(new Callback<ResponseGithub>() {
                @Override
                public void onResponse(Call<ResponseGithub> call, Response<ResponseGithub> response) {
                    if (response.isSuccessful()){
                        showLoading(false);
                        ResponseGithub user = response.body();
                        if (user != null){
                            String usernames = "Username : " + user.getLogin();
                            String name = "Name : " + user.getName();
                            String id = "Id : " + user.getId();
                            String bio = "Bio : " + user.getBio();
                            String lokasi = "Lokasi : " + user.getEmail();
                            String email = "Email : " + user.getEmail();
                            String followers = "Followers : " + "\n" +user.getFollowers();
                            String following = "Following : " + "\n" +user.getFollowing();
                            String repo = "Repositories : " + "\n" +user.getFollowing();
                            String gists = "Gists : " + "\n" +user.getFollowing();
                            String gambar = user.getAvatarUrl();

                            Nama.setText(name);
                            Username.setText(usernames);
                            Id.setText(id);
                            Bio.setText(bio);
                            Lokasi.setText(lokasi);
                            Email.setText(email);
                            Repo.setText(repo);
                            Gists.setText(gists);
                            Followers.setText(followers);
                            Following.setText(following);
                            Picasso.get().load(gambar).into(imageView);
                        }else {
                            Toast.makeText(DetailActivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseGithub> call, Throwable t) {
                    Toast.makeText(DetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}