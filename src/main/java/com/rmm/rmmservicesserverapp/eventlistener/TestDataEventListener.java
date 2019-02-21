package com.rmm.rmmservicesserverapp.eventlistener;

import com.rmm.rmmservicesserverapp.domain.model.*;
import com.rmm.rmmservicesserverapp.repository.CustomerRepository;
import com.rmm.rmmservicesserverapp.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * The listener ensures there is at least one customer and one service in the database.
 */
@Component
public class TestDataEventListener
{
    /**
     * Repository of customers
     */
    private final CustomerRepository customerRepository;

    /**
     * Repository of services
     */
    private final ServiceRepository serviceRepository;

    @Autowired
    public TestDataEventListener(CustomerRepository customerRepository, ServiceRepository serviceRepository)
    {
        this.customerRepository = customerRepository;
        this.serviceRepository  = serviceRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady()
    {
        if (customerRepository.count() == 0) {
            Customer customer = new Customer();
            customerRepository.save(customer);
        }

        if (serviceRepository.count() == 0) {
            Service service = new Service("Test service");

            service.setCost(new ServiceCost(service, DeviceType.WINDOWS_WORKSTATION, 10.0));
            service.setCost(new ServiceCost(service, DeviceType.WINDOWS_SERVER, 20.0));

            serviceRepository.save(service);
        }
    }
}
