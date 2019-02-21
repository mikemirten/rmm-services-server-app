package com.rmm.rmmservicesserverapp.service;

import com.rmm.rmmservicesserverapp.dto.DeviceDTO;
import com.rmm.rmmservicesserverapp.exception.NotFoundException;
import com.rmm.rmmservicesserverapp.domain.model.Customer;
import com.rmm.rmmservicesserverapp.domain.model.Device;
import com.rmm.rmmservicesserverapp.domain.model.DeviceType;
import com.rmm.rmmservicesserverapp.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * An implementation of device-service persisting data using repository
 */
@Service
public final class PersistentDeviceService implements DeviceService
{
    /**
     * Repository of devices
     */
    private final DeviceRepository deviceRepository;

    @Autowired
    public PersistentDeviceService(DeviceRepository deviceRepository)
    {
        this.deviceRepository = deviceRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Device find(int id) throws NotFoundException
    {
        Optional<Device> result = deviceRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        throw new NotFoundException("Device not found by ID: " + id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Device create(Customer customer, DeviceDTO dto)
    {
        Device device = new Device(
            customer,
            DeviceType.valueOf(dto.getType()),
            dto.getName()
        );

        deviceRepository.save(device);

        return device;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Device device, DeviceDTO dto)
    {
        String name = dto.getName();
        String type = dto.getType();

        if (name != null) {
            device.setName(name);
        }

        if (type != null) {
            device.setType(DeviceType.valueOf(type));
        }

        deviceRepository.save(device);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Device device)
    {
        deviceRepository.delete(device);
    }
}
