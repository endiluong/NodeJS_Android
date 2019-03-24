package com.example.admin.nodejs_android.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.nodejs_android.Models.Order;
import com.example.admin.nodejs_android.R;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {

    Activity con;
    int layout;
    ArrayList<Order> orderArray;

    public OrderAdapter(@NonNull Activity context, int resource, @NonNull  ArrayList<Order> objects) {
        super( context, resource, objects );
        this.con = context;
        this.layout = resource;
        this.orderArray = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = con.getLayoutInflater();
        convertView = inflater.inflate( layout,null );

        TextView tv_orderTittle = (TextView)convertView.findViewById(R.id.tv_orderTittle);
        TextView tv_orderContent = (TextView)convertView.findViewById( R.id.tv_orderContent );

        Order temp = orderArray.get( position );
        tv_orderTittle.setText( temp.getOrderTittle() );
        tv_orderContent.setText( temp.getOrderContent() );


        return convertView;
    }
}
