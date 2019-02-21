package com.rmm.rmmservicesserverapp.service;

import com.rmm.rmmservicesserverapp.exception.UnprocessableException;
import com.rmm.rmmservicesserverapp.domain.model.Customer;
import com.rmm.rmmservicesserverapp.exception.NotFoundException;
import com.rmm.rmmservicesserverapp.domain.model.Service;

/**
 * Interface of a customer-service
 */
public interface CustomerService
{
    /**
     * Find customer by ID if exists
     *
     * @param id ID of customer
     * @return   Found customer
     * @throws   NotFoundException
     */
    Customer find(int id) throws NotFoundException;

    /**
     * Add service to customer
     *
     * @param  customer Customer required service
     * @param  service  Service to add
     * @throws UnprocessableException
     */
    void addService(Customer customer, Service service) throws UnprocessableException;

    /**
     * Delete service from customer
     *
     * @param  customer Customer cancelling service
     * @param  service  Service to cancel
     * @throws UnprocessableException
     */
    void cancelService(Customer customer, Service service) throws UnprocessableException;
}
