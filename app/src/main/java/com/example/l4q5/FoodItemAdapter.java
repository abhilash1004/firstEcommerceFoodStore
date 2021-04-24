package com.example.l4q5;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class FoodItemAdapter extends BaseAdapter {
    String[] items,descriptions;
    String[] prices;
    Integer[] qty;
    LayoutInflater mInflator;
    private CheckBox foodCheckBox;
    TextView qtyText;

    public FoodItemAdapter(Context c, String[] i, String[] p, String[] d)
    {
        items = i;
        prices = p;
        descriptions = d;
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        qty = new Integer[i.length];
        for(int x=0;x<i.length;x++)
            qty[x] = 0;
    }

    public void setQty(int position,View v)
    {
        qty[position]++;
        Log.d("Tag", "setQty: "+items[position] + qty[position]);
        qtyText = (TextView) v.findViewById(R.id.quantitytext);
        qtyText.setText("Quantity: "+qty[position]);
    }

    public String getName(int position){
        return items[position];
    }
    public int getToatalAmount(){
        int ans=0;
        for(int i=0;i<qty.length;i++)
        {
            int pri = Integer.parseInt(prices[i]);
            ans += pri*qty[i];
        }
        return ans;
    }

    public Map<String,Integer> itemsSelected()
    {
        Map<String,Integer> hm = new HashMap<String, Integer>();
        for(int i=0;i<qty.length;i++)
        {
            if(qty[i]>0)
            {
                hm.put(items[i],qty[i]);
            }
        }
        return  hm;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =mInflator.inflate(R.layout.food_layout_detail,null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nametextView);
        TextView desTextView = (TextView) v.findViewById(R.id.descriptionTextView);
        TextView priceTextView = (TextView) v.findViewById(R.id.pricetextView);
        ImageView foodImageView = (ImageView) v.findViewById(R.id.foodImageView);

        //foodImageView.setLayoutParams(new ScrollView.LayoutParams(100,100));
        //foodCheckBox = (CheckBox) v.findViewById(R.id.foodcheckBox);

        String name =items[position];
        String des = descriptions[position];
        String cost = prices[position];

        nameTextView.setText(name);
        desTextView.setText(des);
        priceTextView.setText(cost);
        if(position<mThumbIds.length)
        {
            foodImageView.setImageResource(mThumbIds[position]);
        }

        return v;
    }
    private Integer[] mThumbIds = {
            R.drawable.orange,R.drawable.tomato,R.drawable.squash
    };
}
