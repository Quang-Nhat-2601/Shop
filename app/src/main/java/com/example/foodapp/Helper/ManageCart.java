package com.example.foodapp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.foodapp.Domain.ProductDomain;
import com.example.foodapp.Interface.ChangeNumberItemListener;

import java.util.ArrayList;

public class ManageCart {
    private Context context;
    private TinyDB tinyDB;

    public ManageCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(ProductDomain item) {
        ArrayList<ProductDomain> listFood = getListCart();
        boolean existAlready = false;

        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if(listFood.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if(existAlready) {
            listFood.get(n).setNumberInCart(item.getNumberInCart() + 1);
        } else {
            listFood.add(item);
        }

        tinyDB.putListObject("CardList", listFood);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<ProductDomain> getListCart() {
        return tinyDB.getListObject("CardList");
    }

    public void minusNumberFood(ArrayList<ProductDomain> listfood, int position, ChangeNumberItemListener changeNumberItemListener) {
        if(listfood.get(position).getNumberInCart() == 1) {
            listfood.remove(position);
        } else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);
        }

        tinyDB.putListObject("CardList", listfood);
        changeNumberItemListener.changed();
    }

    public void plusNumberFood(ArrayList<ProductDomain> listfood, int position, ChangeNumberItemListener changeNumberItemListener) {
        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemListener.changed();
    }

    public double getTotalFee() {
        ArrayList<ProductDomain> listfood2 = getListCart();
        double fee = 0;
        for (int i = 0; i < listfood2.size(); i++) {
            fee = fee + listfood2.get(i).getFee() * listfood2.get(i).getNumberInCart();
        }
        return fee;
    }
}


