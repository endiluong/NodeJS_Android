package com.example.admin.nodejs_android.Services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.example.admin.nodejs_android.DAO.daoOrder;
import com.example.admin.nodejs_android.Models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    Context context;
    Order order;
    daoOrder myDAO;
    ArrayList<Order> arrCat;
    List<Order> lsCat;

    public OrderService(Context context) {
        this.context = context;
        myDAO = daoOrder.getInstance( context );
    }

    public void  delCat(final int index){
        // Do Delete
    }

    public Boolean updateOrder(Order order){
        if (order.getId().isEmpty()){
            Toast.makeText( context,"Null",Toast.LENGTH_SHORT ).show();
        }
        else if(checkValid(order.getId())){
            //CREATE USER
            Order temp = new Order();
            temp.setOrderTittle( order.getOrderTittle() );
            temp.setOrderContent( order.getOrderContent() );
            myDAO.updateCat(temp);
            return true;
        } else {
            return false;
        }

        return false;
    }

    public Boolean addOrder(Order order){
//        Log.i("ORDERID", "iD" + order.getId());
//        if (order.getId().isEmpty()){
//            Toast.makeText( context,"Null",Toast.LENGTH_SHORT ).show();
//        }
//        else if(checkValid(order.getId())){
//            //CREATE USER
            Order temp = new Order();
            temp.setOrderTittle( order.getOrderTittle() );
            temp.setOrderContent( order.getOrderContent() );
            myDAO.insertCat(temp);
            return true;
//        } else {
//            return false;
//        }
//        return false;
    }

    public Boolean checkValid(String id){
        lsCat= new ArrayList<>();
        lsCat= myDAO.getAllItem();
//        Order order = new Order();
        for (int i = 0; i < lsCat.size(); i++) {
            if (id.equals(lsCat.get(i).getId())) {
                Toast.makeText(context, "FAIL", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
