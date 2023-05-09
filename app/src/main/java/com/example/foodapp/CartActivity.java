package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodapp.Adapter.CardListAdapter;
import com.example.foodapp.Helper.ManageCart;
import com.example.foodapp.Interface.ChangeNumberItemListener;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManageCart manageCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        manageCart = new ManageCart(this);

        initView();

        initList();
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        adapter = new CardListAdapter(manageCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(manageCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard(){
        double percentTax = 0.1;
        double delivery = 10;
        double tax = Math.round((manageCart.getTotalFee() * percentTax) * 100.0) / 100.0;
        double total = Math.round((manageCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(manageCart.getTotalFee() * 100.0) / 100.0;

        totalFeeTxt.setText("$" + itemTotal);
        deliveryTxt.setText("$" + delivery);
        taxTxt.setText("$" + tax);
        totalTxt.setText("$" + total);
    }

    private void initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);

        emptyTxt = findViewById(R.id.emptyText);

        recyclerViewList = findViewById(R.id.view);
        scrollView = findViewById(R.id.cart_scrollview);
    }
}