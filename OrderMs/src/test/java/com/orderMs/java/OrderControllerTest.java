package com.orderMs.java;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.orderMs.java.controller.OrderController;
import com.orderMs.java.entities.Order;
import com.orderMs.java.service.OrderService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class)
@WithMockUser
public class OrderControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderService orderService;
	List<Order> mockOrders = new ArrayList<Order>();

	Order mockOrder = new Order(1, "Jeans", 1200, null);

	@org.junit.jupiter.api.Test
	public void getOrderById() throws Exception {
		Mockito.when(orderService.retrieveOrders(Mockito.anyLong())).thenReturn(mockOrder);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{id:1,name:Jeans,amount:1200,date:null}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	@Test
	public void getOrders() throws Exception {
		mockOrders.add(new Order(1, "abc", 1200, null));
		mockOrders.add(new Order(2, "as", 700, null));
		Mockito.when(orderService.retrieveAllOrders()).thenReturn(mockOrders);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected="[{id:1,name:abc,amount:1200.0,date:null},{id:2,name:as,amount:700.0,date:null}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}
