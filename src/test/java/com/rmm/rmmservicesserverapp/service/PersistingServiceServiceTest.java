package com.rmm.rmmservicesserverapp.service;

import com.rmm.rmmservicesserverapp.domain.model.Service;
import com.rmm.rmmservicesserverapp.repository.ServiceRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PersistingServiceServiceTest
{
    @Test
    public void find()
    {
        Service service = mock(Service.class);
        Optional<Service> optional = Optional.of(service);

        ServiceRepository repository = mock(ServiceRepository.class);
        when(repository.findById(any(Integer.class))).thenReturn(optional);

        ServiceService serviceService = new PersistingServiceService(repository);
        Service result = serviceService.find(1);

        verify(repository).findById(1);

        assertNotNull(result);
        assertSame(service, result);
    }

    @Test
    public void all()
    {
        Iterable<Service> iterable = mock(Iterable.class);

        ServiceRepository repository = mock(ServiceRepository.class);
        when(repository.findAll()).thenReturn(iterable);

        ServiceService serviceService = new PersistingServiceService(repository);
        Iterable<Service> result = serviceService.all();

        assertSame(iterable, result);
    }
}
