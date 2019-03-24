package com.example.admin.nodejs_android.Services;

import android.content.Context;
import android.widget.Toast;

import com.example.admin.nodejs_android.DAO.daoUsers;
import com.example.admin.nodejs_android.Models.User;

import java.util.ArrayList;
import java.util.List;

public class SignUpService {
    Context context;
    List<User> usersList;
    daoUsers myDAO;

    public SignUpService(Context context) {
        this.context = context;
        myDAO= daoUsers.getInstance(context);
    }

    public boolean SignUp(String username, String password){
        if(Validator(username,password)){
            //CREATE USER
            User temp = new User(username,password);
            myDAO.insertUser(temp);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean Validator(String username, String password){
        // GET THE USER LIST FROM DATABASE TO CHECK FOR DUPLICATE
        usersList= new ArrayList<>();
        usersList= myDAO.getAllItem();
        //
        // Create temporary User
        User user = new User(username,password);
        //
        // Check user list for duplicate username
        //
        for (int i = 0; i < usersList.size(); i++) {
            if ((user.getUsername()).equals(usersList.get(i).getUsername())) {
                Toast.makeText(context, "Duplicate Username, please use another username", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }


}
