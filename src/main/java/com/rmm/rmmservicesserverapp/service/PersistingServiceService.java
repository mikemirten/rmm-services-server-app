package com.rmm.rmmservicesserverapp.service;

import com.rmm.rmmservicesserverapp.exception.NotFoundException;
import com.rmm.rmmservicesserverapp.domain.model.Service;
import com.rmm.rmmservicesserverapp.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * An implementation of service of services persisting data using repository
 */
@org.springframework.stereotype.Service
public final class PersistingServiceService implements ServiceService
{
    /**
     * Repository of services
     */
    private final ServiceRepository serviceRepository;

    @Autowired
    public PersistingServiceService(ServiceRepository serviceRepository)
    {
        this.serviceRepository = serviceRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Service find(int id) throws NotFoundException
    {
        Optional<Service> result = serviceRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        throw new NotFoundException("Service not found by ID: " + id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Service> all()
    {
        return serviceRepository.findAll();
    }
}
