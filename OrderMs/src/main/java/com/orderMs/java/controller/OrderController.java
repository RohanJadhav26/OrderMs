package com.orderMs.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderMs.java.entities.Order;
import com.orderMs.java.service.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@GetMapping("")
	public List<Order> getOrders(){
		return orderService.retrieveAllOrders();
	}
	
	@GetMapping("{id}")
	public Order getOrderById(@PathVariable("id") long id) {
		return orderService.retrieveOrders(id);
	}
	@PostMapping("")
		public void addOrders(@RequestBody Order order) {
			orderService.addOrders(order);
		}
	
	
	@PatchMapping("{id}")
	public void updateOrders(@RequestBody Order order,@PathVariable long id) {
		order.setId(id);
		orderService.updateOrder(order);
	}
	@DeleteMapping("{id}")
	public void deleteOrder(@PathVariable long id) {
		orderService.deleteOrder(id);
	}
}
