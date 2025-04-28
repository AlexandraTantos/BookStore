package com.example.bookstore.service;

import com.example.bookstore.model.*;
import com.example.bookstore.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;
    private OrderItem orderItem;
    private Book book;
    private Page<Order> orderPage;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        Author author = new Author("Author Name");
        Genre genre = new Genre("Genre Name");

        book = new Book("Book Title", author, genre,100.0);

        orderItem = new OrderItem(order, book, 2);
        order = new Order();
        order.setOrderItems(Arrays.asList(orderItem));
        order.setTotalPrice(200.0);

        orderPage = new PageImpl<>(Arrays.asList(order));
    }

    @Test
    void testCreateOrder(){
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order result = orderService.createOrder(order);

        assertEquals(200.0, result.getTotalPrice());
        assertEquals(1, result.getOrderItems().size());
    }

    @Test
    void testGetAllOrders(){
        when(orderRepository.findAll(PageRequest.of(0, 10))).thenReturn(orderPage);

        Page<Order> result = orderService.getAllOrders(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals(200.0, result.getContent().get(0).getTotalPrice());
    }

    @Test
    void testGetOrderById(){
        when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));

        Order result = orderService.getOrderById(1L);

        assertEquals(200.0, result.getTotalPrice());
    }
    @Test
    void testGetOrderHistoryForCustomer(){
        Customer customer = new Customer("testcustomer@gmail.com");

        order.setCustomer(customer);

        when(orderRepository.findByCustomerId(customer.getId(), PageRequest.of(0, 10))).thenReturn(orderPage);

        Page<Order> result = orderService.getOrderHistoryForCustomer(customer.getId(), PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals(200.0, result.getContent().get(0).getTotalPrice());
        assertEquals("testcustomer@gmail.com", result.getContent().get(0).getCustomer().getEmail());
    }

}
