package com.example.foodapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Domain.ProductDomain;
import com.example.foodapp.R;
import com.example.foodapp.ShowDetailActivity;

import java.util.ArrayList;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder>{
    ArrayList<ProductDomain> RecommendedDomains;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        ImageView pic;
        ImageView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }

    public RecommendedAdapter(ArrayList<ProductDomain> categoryDomains) {
        this.RecommendedDomains = categoryDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recommended, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(RecommendedDomains.get(position).getTitle());
        holder.fee.setText(String.valueOf(RecommendedDomains.get(position).getFee()));

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(RecommendedDomains.get(position).getPic(), "drawable",
                        holder.itemView.getContext().getPackageName());

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDomain selectedFood = RecommendedDomains.get(position);
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("title", selectedFood.getTitle());
                intent.putExtra("fee", selectedFood.getFee());
                intent.putExtra("des", selectedFood.getDescription());
                intent.putExtra("material", selectedFood.getMaterial());
                intent.putExtra("star", selectedFood.getStar());
                intent.putExtra("pic", selectedFood.getPic());

                holder.itemView.getContext().startActivity(intent);
            }
        });

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return RecommendedDomains.size();
    }
}
