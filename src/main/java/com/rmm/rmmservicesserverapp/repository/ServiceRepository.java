package com.rmm.rmmservicesserverapp.repository;

import com.rmm.rmmservicesserverapp.domain.model.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Integer> {
}
