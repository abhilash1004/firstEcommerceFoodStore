package com.example.l4q5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView1;
    String[] items,descriptions,cost;
   // Integer[] cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT);
        listView1 =(ListView) findViewById(R.id.listView1);
        items = res.getStringArray(R.array.items);
        descriptions = res.getStringArray(R.array.descriptions);
        cost = res.getStringArray(R.array.prices);


        final FoodItemAdapter itemAdapter = new FoodItemAdapter(this,items,cost,descriptions);
        listView1.setAdapter(itemAdapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               FoodItemAdapter fooditem =  (FoodItemAdapter) parent.getAdapter();
                //Toast.makeText(getApplicationContext(),fooditem.getName(position)+"Hello",Toast.LENGTH_SHORT);
                Log.d("FoodName: ",fooditem.getName(position));
                fooditem.setQty(position,view);
            }
        });

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = itemAdapter.getToatalAmount();
                Toast.makeText(getApplicationContext(),""+total,Toast.LENGTH_SHORT);
                Log.d("Total Quantity: ",""+total);
                Map<String,Integer> hm = itemAdapter.itemsSelected();
                String itemList;
                itemList = "";
                for(Map.Entry<String,Integer> me:hm.entrySet())
                {
                    itemList += me.getKey() + ": "+me.getValue() + "\n";
                }
                Log.d("Total Items: ",itemList);
                //int x = itemAdapter.getToatalAmount();
                Intent ShowFoodActivity = new Intent(getApplicationContext(),OrderDetailActivity.class);
                ShowFoodActivity.putExtra("com.example.foodList",itemList);
                ShowFoodActivity.putExtra("com.example.totAmt",total);
                startActivity(ShowFoodActivity);
            }
        });
    }
}