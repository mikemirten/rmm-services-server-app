package com.rmm.rmmservicesserverapp.controller;

import com.rmm.rmmservicesserverapp.action.Create;
import com.rmm.rmmservicesserverapp.action.Update;
import com.rmm.rmmservicesserverapp.dto.DeviceDTO;
import com.rmm.rmmservicesserverapp.domain.model.Customer;
import com.rmm.rmmservicesserverapp.domain.model.Device;
import com.rmm.rmmservicesserverapp.exception.NotFoundException;
import com.rmm.rmmservicesserverapp.jsonapi.*;
import com.rmm.rmmservicesserverapp.service.CustomerService;
import com.rmm.rmmservicesserverapp.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1")
public class DeviceController
{
    /**
     * Customers service
     */
    private final CustomerService customerService;

    /**
     * Devices service
     */
    private final DeviceService deviceService;

    @Autowired
    public DeviceController(
        CustomerService customerService,
        DeviceService   deviceService
    ) {
        this.customerService = customerService;
        this.deviceService   = deviceService;
    }

    @ModelAttribute
    public void handleResponse(HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    /**
     * Get list of devices of certain customer
     *
     * @param id ID of customer
     * @return DataResponse with list of devices
     * @throws NotFoundException
     */
    @GetMapping("/customers/{id}/devices")
    public DataResponse<Iterable<Device>> list(@PathVariable int id) throws NotFoundException
    {
        Iterable<Device> devices = customerService.find(id).getDevices();

        return new DataResponse<>(devices);
    }

    /**
     * Create new device for certain customer
     *
     * @param id ID of customer
     * @param request Request with attributes of device
     * @return DataResponse with just created device
     */
    @PostMapping("/customers/{id}/devices")
    @ResponseStatus(HttpStatus.CREATED)
    public DataResponse<Device> create(
        @PathVariable int id,
        @RequestBody @Validated(Create.class) Request<DeviceDTO> request
    ) throws NotFoundException
    {
        Customer customer = customerService.find(id);

        Device device = deviceService.create(customer, request.getData());

        return new DataResponse<>(device);
    }

    /**
     * Get device by ID
     *
     * @param id ID of device
     * @return Device
     * @throws NotFoundException
     */
    @GetMapping("/devices/{id}")
    public DataResponse<Device> get(@PathVariable int id) throws NotFoundException
    {
        return new DataResponse<>(deviceService.find(id));
    }

    /**
     * Update existing device
     *
     * @param id ID of device
     * @param request Request with update(s)
     * @return Device with updated data
     */
    @PatchMapping("/devices/{id}")
    public DataResponse<Device> update(
        @PathVariable int id,
        @RequestBody @Validated(Update.class) Request<DeviceDTO> request
    ) throws NotFoundException
    {
        Device device = deviceService.find(id);

        deviceService.update(device, request.getData());

        return new DataResponse<>(device);
    }

    /**
     * Delete existing device
     *
     * @param id ID of device
     */
    @DeleteMapping("/devices/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) throws NotFoundException
    {
        Device device = deviceService.find(id);

        deviceService.delete(device);
    }
}
