package com.urely.shd2014.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.urely.shd2014.entity.Customer;
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
