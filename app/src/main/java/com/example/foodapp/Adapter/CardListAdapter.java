package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Domain.ProductDomain;
import com.example.foodapp.Helper.ManageCart;
import com.example.foodapp.Interface.ChangeNumberItemListener;
import com.example.foodapp.R;

import java.util.ArrayList;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder>{
    ArrayList<ProductDomain> listFoodSelected;
    private ManageCart manageCart;
    ChangeNumberItemListener changeNumberItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.fee);
            totalEachItem = itemView.findViewById(R.id.addBtn);

            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            num = itemView.findViewById(R.id.numberItemCart);
        }
    }

    public CardListAdapter(ArrayList<ProductDomain> listFoodSelected,
                           Context context,
                           ChangeNumberItemListener changeNumberItemListener) {
        this.listFoodSelected = listFoodSelected;
        this.manageCart = new ManageCart(context);
        this.changeNumberItemListener =changeNumberItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(listFoodSelected.get(position).getTitle());
        holder.feeEachItem.setText("$" + listFoodSelected.get(position).getFee());
        holder.totalEachItem.setText("$" + Math.round(listFoodSelected.get(position).getNumberInCart() * listFoodSelected.get(position).getFee()));
        holder.num.setText(String.valueOf(listFoodSelected.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(listFoodSelected.get(position).getPic(), "drawable",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyDataSetChanged();
                changeNumberItemListener.changed();
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyDataSetChanged();
                changeNumberItemListener.changed();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFoodSelected.size();
    }
}
