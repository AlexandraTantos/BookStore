package com.example.bookstore.service;

import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderItem;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Page<Order> getAllOrders(Pageable pageable){
        return orderRepository.findAll(pageable);
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));
    }

    public Order createOrder(Order order) {
        double total = 0.0;

        for (OrderItem item : order.getOrderItems()) {
            total += item.getBook().getPrice() * item.getQuantity();
        }

        order.setTotalPrice(total);

        return orderRepository.save(order);
    }


    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public Page<Order> getOrderHistoryForCustomer(Long customerId, Pageable pageable) {
        return orderRepository.findByCustomerId(customerId, pageable);
    }

}
