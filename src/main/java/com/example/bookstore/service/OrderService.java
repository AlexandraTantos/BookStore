package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Customer;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderItem;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
    }

    public Page<Order> getAllOrders(Pageable pageable){
        return orderRepository.findAll(pageable);
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found!"));
    }

    public Order createOrder(Order order) {
        double total = 0.0;

        Customer customer = customerRepository.findById(order.getCustomer().getId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));
        order.setCustomer(customer);

        for (OrderItem item : order.getOrderItems()) {
            Book book = bookRepository.findById(item.getBook().getId());

            item.setBook(book);
            item.setOrder(order);
            total += book.getPrice() * item.getQuantity();
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