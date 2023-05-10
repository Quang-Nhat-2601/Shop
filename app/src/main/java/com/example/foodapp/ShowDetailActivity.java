package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Domain.ProductDomain;
import com.example.foodapp.Helper.ManageCart;

import java.text.DecimalFormat;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titleText, feeTxt, descriptionTxt, numberOrderTxt;
    private TextView totalPriceTxt, startTxt, materialTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private int numberOrder = 1;
    private ManageCart manageCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        manageCart = new ManageCart(this);
        iniView();

        getBundle();
    }

    private void getBundle() {
        String title = getIntent().getStringExtra("title");
        Double fee = getIntent().getDoubleExtra("fee", 0);
        String desc = getIntent().getStringExtra("des");
        int star = getIntent().getIntExtra("star", 0);
        int time = getIntent().getIntExtra("time", 0);
        String material = getIntent().getStringExtra("material");

        String pic = getIntent().getStringExtra("pic");


        int drawableResourceId = this.getResources().getIdentifier(pic,
                "drawable",
                this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleText.setText(title);
        feeTxt.setText("$" + fee);
        descriptionTxt.setText(desc);
        numberOrderTxt.setText(String.valueOf(numberOrder));
        materialTxt.setText(material);
        startTxt.setText(""+star);

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder + 1;

                DecimalFormat df = new DecimalFormat("#.##");
                double roundedNumber = Double.parseDouble(df.format(numberOrder * fee));

                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText(String.valueOf(roundedNumber));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText(String.valueOf(numberOrder * fee));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDomain temp = new ProductDomain(
                        title, pic, desc, fee, star, material
                );
                temp.setNumberInCart(numberOrder);
                manageCart.insertFood(temp);
            }
        });
    }

    private void iniView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleText = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberItemTxt);
        plusBtn = findViewById(R.id.plusCartBtn);
        minusBtn = findViewById(R.id.minusCartBtn);
        picFood = findViewById(R.id.foodPic);
        totalPriceTxt = findViewById(R.id.totalPriceTxt);
        startTxt = findViewById(R.id.starTxt);
        materialTxt = findViewById(R.id.materialText);
    }
}