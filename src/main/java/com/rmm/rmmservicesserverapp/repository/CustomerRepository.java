package com.rmm.rmmservicesserverapp.repository;

import com.rmm.rmmservicesserverapp.domain.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
