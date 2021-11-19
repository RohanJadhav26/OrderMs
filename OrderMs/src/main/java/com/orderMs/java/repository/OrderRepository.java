package com.orderMs.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderMs.java.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
