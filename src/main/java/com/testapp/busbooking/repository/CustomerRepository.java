package com.testapp.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testapp.busbooking.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
