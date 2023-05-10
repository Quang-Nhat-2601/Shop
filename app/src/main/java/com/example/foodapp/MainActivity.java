package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.foodapp.Adapter.CategoryAdapter;
import com.example.foodapp.Adapter.RecommendedAdapter;
import com.example.foodapp.Domain.CategoryDomain;
import com.example.foodapp.Domain.ProductDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.view1);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList= new ArrayList<>();
        categoryList.add(new CategoryDomain("Funiture", "chairs"));
        categoryList.add(new CategoryDomain("Home Deco", "curtains"));
        categoryList.add(new CategoryDomain("Household", "tv"));
        categoryList.add(new CategoryDomain("Kitchenware", "pan1"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<ProductDomain> foodlist = new ArrayList<>();
        foodlist.add(new ProductDomain("Pans", "pan", "a metal container that is round and often has a longhandle and a lid, used for cooking things on top of a cooker", 10.0, 5, "Inox"));
        foodlist.add(new ProductDomain("Pans", "pan", "a metal container that is round and often has a longhandle and a lid, used for cooking things on top of a cooker", 10.0, 5, "Inox"));
        foodlist.add(new ProductDomain("Pans", "pan", "a metal container that is round and often has a longhandle and a lid, used for cooking things on top of a cooker", 10.0, 5, "Inox"));
        foodlist.add(new ProductDomain("Pans", "pan", "a metal container that is round and often has a longhandle and a lid, used for cooking things on top of a cooker", 10.0, 5, "Inox"));

        adapter2 = new RecommendedAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }
}