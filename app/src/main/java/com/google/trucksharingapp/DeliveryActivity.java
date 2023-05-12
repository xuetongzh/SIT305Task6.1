package com.google.trucksharingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.trucksharingapp.adapter.DeliveryAdapter;
import com.google.trucksharingapp.bean.DeliveryBean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {
    private List<DeliveryBean> deliveryBeans = new ArrayList<>();
    private RecyclerView recyclerView;
    private DeliveryAdapter deliveryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        recyclerView = findViewById(R.id.deliveryRec);
        deliveryBeans = LitePal.findAll(DeliveryBean.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        deliveryAdapter = new DeliveryAdapter(this, deliveryBeans);
        recyclerView.setAdapter(deliveryAdapter);
    }
}