package com.example.customers.repository;

import com.example.customers.entity.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRepo  extends JpaRepository<customer, Long> {

    // Empty Body
}
