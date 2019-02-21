package com.rmm.rmmservicesserverapp.service;

import com.rmm.rmmservicesserverapp.exception.NotFoundException;
import com.rmm.rmmservicesserverapp.domain.model.Service;

/**
 * Interface of a service of services
 */
public interface ServiceService
{
    /**
     * Find service by ID if exists
     *
     * @param id ID of service
     * @return   Found service
     * @throws   NotFoundException
     */
    Service find(int id) throws NotFoundException;

    /**
     * Get all existing services
     *
     * @return An iterator of found services
     */
    Iterable<Service> all();
}
