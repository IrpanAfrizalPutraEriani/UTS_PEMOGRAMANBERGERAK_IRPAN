package com.example.utspemogramanbergerakirpan.UI;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utspemogramanbergerakirpan.R;
import com.example.utspemogramanbergerakirpan.RESPONSE.ItemsItem;
import com.example.utspemogramanbergerakirpan.RESPONSE.ResponseUser;
import com.example.utspemogramanbergerakirpan.RETROFIT.GitHubService;
import com.example.utspemogramanbergerakirpan.RETROFIT.GithubConfig;
import com.example.utspemogramanbergerakirpan.UI.UserAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.Tampilan);

        GitHubService apiService = GithubConfig.getServices();
        Call<ResponseUser> call = apiService.getUsers("Irpan");

        call.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ItemsItem> itemsItems = response.body().getItems();
                    adapter = new UserAdapter(itemsItems);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get users", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}