package com.rmm.rmmservicesserverapp.service;

import com.rmm.rmmservicesserverapp.domain.model.Customer;
import com.rmm.rmmservicesserverapp.domain.model.Device;
import com.rmm.rmmservicesserverapp.domain.model.DeviceType;
import com.rmm.rmmservicesserverapp.dto.DeviceDTO;
import com.rmm.rmmservicesserverapp.repository.DeviceRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PersistentDeviceServiceTest
{
    @Test
    public void find()
    {
        Device device = mock(Device.class);
        Optional<Device> optional = Optional.of(device);

        DeviceRepository repository = mock(DeviceRepository.class);
        when(repository.findById(any(Integer.class))).thenReturn(optional);

        DeviceService service = new PersistentDeviceService(repository);
        Device result = service.find(1);

        verify(repository).findById(1);

        assertNotNull(result);
        assertSame(device, result);
    }

    @Test
    public void create()
    {
        DeviceRepository repository = mock(DeviceRepository.class);
        DeviceService service = new PersistentDeviceService(repository);

        Customer customer = mock(Customer.class);
        DeviceDTO dto = new DeviceDTO("Test", "WINDOWS_WORKSTATION");

        Device result = service.create(customer, dto);

        verify(repository).save(any(Device.class));

        assertEquals("Test", result.getName());
        assertEquals(DeviceType.WINDOWS_WORKSTATION, result.getType());
    }

    @Test
    public void update()
    {
        DeviceRepository repository = mock(DeviceRepository.class);
        DeviceService service = new PersistentDeviceService(repository);

        Device device = mock(Device.class);
        DeviceDTO dto = new DeviceDTO("Test2", "WINDOWS_SERVER");

        service.update(device, dto);

        verify(repository).save(device);
        verify(device).setName("Test2");
        verify(device).setType(DeviceType.WINDOWS_SERVER);
    }

    @Test
    public void dellete()
    {
        DeviceRepository repository = mock(DeviceRepository.class);
        DeviceService service = new PersistentDeviceService(repository);

        Device device = mock(Device.class);
        service.delete(device);

        verify(repository).delete(device);
    }
}
