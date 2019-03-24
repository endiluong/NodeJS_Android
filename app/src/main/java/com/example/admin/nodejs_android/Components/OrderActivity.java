package com.example.admin.nodejs_android.Components;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.admin.nodejs_android.Adapter.OrderAdapter;
import com.example.admin.nodejs_android.DAO.daoOrder;
import com.example.admin.nodejs_android.Models.Order;
import com.example.admin.nodejs_android.R;
import com.example.admin.nodejs_android.Services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    ListView lv_Order;
    Order order;
    OrderService service;

    private ArrayAdapter<Order> adapter;
    private daoOrder dao_Order;
    private List<Order> listOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv_Order = findViewById(R.id.lv_orderList);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Dialog_Add(view);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        dao_Order = daoOrder.getInstance(this);
        listOrder = dao_Order.getAllItem();

        adapter = new OrderAdapter( this,R.layout.order_item,(ArrayList<Order>)listOrder );
        lv_Order.setAdapter( adapter );
//        mList_type.clear();
        adapter.notifyDataSetChanged();

//        lv_Order.setOnItemClickListener(this);
    }

    public void Dialog_Add(View view){

        LayoutInflater inflater = getLayoutInflater();
        view = inflater.inflate( R.layout.dialog_add_order,null );


        final EditText etOrderTittle = (EditText)view.findViewById( R.id.et_order_tittle );
        final EditText etOrderContent = (EditText)view.findViewById( R.id.et_order_content );

        AlertDialog.Builder mBuilder = new AlertDialog.Builder( this );
        mBuilder.setView( view ).setTitle("ADD CATEGORY").setPositiveButton( "Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                OrderService orderService = new OrderService( getBaseContext() );
                Order temp = new Order(
                        etOrderTittle.getText().toString(),
                        etOrderContent.getText().toString()
                );
                Log.i("AAAAAAAA",temp+"");
                orderService.addOrder(temp);

                temp = null;
                finish();
                startActivityForResult( new Intent( getBaseContext(),OrderActivity.class ),0 );
            }
        } ).setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                dialogInterface.cancel();
            }
        } ).create().show();

    }
}
