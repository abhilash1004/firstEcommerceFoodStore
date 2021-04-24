package com.example.l4q5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        String foodList =getIntent().getExtras().getString("com.example.foodList");
        int totAmt = getIntent().getExtras().getInt("com.example.totAmt");
        TextView list = (TextView) findViewById(R.id.foodOrderList);
        TextView tot = (TextView) findViewById(R.id.finalAmt);
        list.setText(foodList);
        tot.setText(""+totAmt);
    }
}