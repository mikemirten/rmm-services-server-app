package com.rmm.rmmservicesserverapp.service;

import com.rmm.rmmservicesserverapp.exception.NotFoundException;
import com.rmm.rmmservicesserverapp.exception.UnprocessableException;
import com.rmm.rmmservicesserverapp.domain.model.Customer;
import com.rmm.rmmservicesserverapp.domain.model.Service;
import com.rmm.rmmservicesserverapp.repository.CustomerRepository;

import java.util.Optional;

/**
 * An implementation of customer-service persisting data using repository
 */
@org.springframework.stereotype.Service
public final class PersistingCustomerService implements CustomerService
{
    /**
     * Repository of customers
     */
    private final CustomerRepository customerRepository;

    public PersistingCustomerService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer find(int id) throws NotFoundException
    {
        Optional<Customer> result = customerRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        throw new NotFoundException("Customer not found by ID: " + id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addService(Customer customer, Service service) throws UnprocessableException
    {
        if (customer.hasService(service)) {
            throw new UnprocessableException(String.format(
                "Customer #%s already has service #%s added before.",
                customer.getId(),
                service.getId()
            ));
        }

        customer.addService(service);
        customerRepository.save(customer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelService(Customer customer, Service service) throws UnprocessableException
    {
        if (! customer.hasService(service)) {
            throw new UnprocessableException(String.format(
                "Customer #%s has no service #%s added.",
                customer.getId(),
                service.getId()
            ));
        }

        customer.removeService(service);
        customerRepository.save(customer);
    }
}
