package com.orderMs.java.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orderMs.java.entities.Order;
import com.orderMs.java.repository.OrderRepository;

@Component
public class OrderService {
	@Autowired
	OrderRepository orderRepository;

	public List<Order> retrieveAllOrders() {
		return orderRepository.findAll() ;
	}

	public Order retrieveOrders(long id) {
		return orderRepository.findById(id).get();
	}
	
	public void addOrders(Order order) {
		orderRepository.save(order);
	}
	public void deleteOrder(long id) {
		orderRepository.deleteById(id);
	}
	public void updateOrder(Order order) {
		try {
			orderRepository.findById(order.getId()).get();
			orderRepository.save(order);
		} catch (Exception e) {
			System.err.println("No element Found");
		}
	}
	
}
