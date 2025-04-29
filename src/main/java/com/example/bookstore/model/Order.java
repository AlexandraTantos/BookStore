package com.example.bookstore.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    @JsonBackReference
    private List<OrderItem> orderItems;

    private double totalPrice;

    public Order(){
    }

    public Order(Customer customer){
        this.customer = customer;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
