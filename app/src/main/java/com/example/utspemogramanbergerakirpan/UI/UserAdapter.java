package com.example.utspemogramanbergerakirpan.UI;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utspemogramanbergerakirpan.R;
import com.example.utspemogramanbergerakirpan.RESPONSE.ItemsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<ItemsItem> users;

    public UserAdapter(List<ItemsItem> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemsItem user = users.get(position);
        holder.username.setText(user.getLogin());
        Picasso.get().load(user.getAvatarUrl()).into(holder.avatar);

        holder.itemView.setOnClickListener(click -> {
            Intent intent = new Intent(click.getContext(), DetailActivity.class);
            intent.putExtra("username", user.getLogin());
            intent.putExtra("avatarUrl", user.getAvatarUrl());
            click.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView username;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.Gambar);
            username = itemView.findViewById(R.id.TeksNama);
        }
    }
}