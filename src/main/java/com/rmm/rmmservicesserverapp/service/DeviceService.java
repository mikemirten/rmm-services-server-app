package com.rmm.rmmservicesserverapp.service;

import com.rmm.rmmservicesserverapp.dto.DeviceDTO;
import com.rmm.rmmservicesserverapp.domain.model.Customer;
import com.rmm.rmmservicesserverapp.domain.model.Device;
import com.rmm.rmmservicesserverapp.exception.NotFoundException;

/**
 * Interface of a Device-service
 */
public interface DeviceService
{
    /**
     * Find device by ID
     *
     * @param id ID of device
     * @return   Found device
     * @throws   NotFoundException
     */
    Device find(int id) throws NotFoundException;

    /**
     * Create new device for given customer
     *
     * @param customer Owner of new device
     * @param dto      DTO with attributes of new device
     * @return         Created device
     */
    Device create(Customer customer, DeviceDTO dto);

    /**
     * Update existing device
     *
     * @param device Device to update
     * @param dto    DTO with updates
     */
    void update(Device device, DeviceDTO dto);

    /**
     * Delete existing device
     *
     * @param device Device to delete
     */
    void delete(Device device);
}
