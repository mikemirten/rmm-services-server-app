package com.rmm.rmmservicesserverapp.service;

import com.rmm.rmmservicesserverapp.domain.model.Customer;
import com.rmm.rmmservicesserverapp.domain.model.Service;
import com.rmm.rmmservicesserverapp.exception.UnprocessableException;
import com.rmm.rmmservicesserverapp.repository.CustomerRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PersistingCustomerServiceTest
{
    @Test
    public void create()
    {
        Customer customer = mock(Customer.class);
        Optional<Customer> optional = Optional.of(customer);

        CustomerRepository repository = mock(CustomerRepository.class);
        when(repository.findById(any(Integer.class))).thenReturn(optional);

        CustomerService service = new PersistingCustomerService(repository);
        Customer result = service.find(1);

        verify(repository).findById(1);

        assertNotNull(result);
        assertSame(customer, result);
    }

    @Test
    public void addService()
    {
        Customer customer = mock(Customer.class);
        Service service   = mock(Service.class);

        when(customer.hasService(service)).thenReturn(false);

        CustomerRepository repository = mock(CustomerRepository.class);

        CustomerService customerService = new PersistingCustomerService(repository);
        customerService.addService(customer, service);

        verify(customer).addService(service);
        verify(repository).save(customer);
    }

    @Test(expected = UnprocessableException.class)
    public void addServiceFail()
    {
        Customer customer = mock(Customer.class);
        Service service   = mock(Service.class);

        when(customer.hasService(service)).thenReturn(true);

        CustomerRepository repository = mock(CustomerRepository.class);

        CustomerService customerService = new PersistingCustomerService(repository);
        customerService.addService(customer, service);
    }

    @Test
    public void cancelService()
    {
        Customer customer = mock(Customer.class);
        Service service   = mock(Service.class);

        when(customer.hasService(service)).thenReturn(true);

        CustomerRepository repository = mock(CustomerRepository.class);

        CustomerService customerService = new PersistingCustomerService(repository);
        customerService.cancelService(customer, service);

        verify(customer).removeService(service);
        verify(repository).save(customer);
    }

    @Test(expected = UnprocessableException.class)
    public void cancelServiceFail()
    {
        Customer customer = mock(Customer.class);
        Service service   = mock(Service.class);

        when(customer.hasService(service)).thenReturn(false);

        CustomerRepository repository = mock(CustomerRepository.class);

        CustomerService customerService = new PersistingCustomerService(repository);
        customerService.cancelService(customer, service);
    }
}
