package com.springproject.expproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springproject.expproject.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	Optional <Order> findByOrderidAndUserId(Long id, Long userId);
}
