package com.example.admin.nodejs_android.Models;

public class Order {
    private String id;
    private String orderTittle;
    private String orderContent;

    public Order() {
    }

    public Order(String orderTittle, String orderContent) {
        this.orderTittle = orderTittle;
        this.orderContent = orderContent;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getOrderTittle() {
        return orderTittle;
    }

    public void setOrderTittle(String orderTittle) {
        this.orderTittle = orderTittle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

