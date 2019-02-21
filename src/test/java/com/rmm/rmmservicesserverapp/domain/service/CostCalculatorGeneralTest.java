package com.rmm.rmmservicesserverapp.domain.service;

import com.rmm.rmmservicesserverapp.domain.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CostCalculatorGeneralTest
{
    @Test
    public void calculateCost()
    {
        Customer customer = mock(Customer.class);

        List<Device> devices = prepareDevices();
        when(customer.getDevices()).thenReturn(devices);

        List<Service> services = prepareServices();
        when(customer.getServices()).thenReturn(services);

        CostCalculator calculator  = new CostCalculatorGeneral();
        Map<String, Double> result = calculator.calculateCost(customer);

        assertEquals(5, result.size());

        assertTrue(result.containsKey("Output"));
        assertTrue(result.containsKey("Devices"));
        assertTrue(result.containsKey("Antivirus"));
        assertTrue(result.containsKey("Cloudberry"));
        assertTrue(result.containsKey("TeamViewer"));


        assertEquals(71.0, result.get("Output"), 0.01);
        assertEquals(20.0, result.get("Devices"), 0.01);
        assertEquals(31.0, result.get("Antivirus"), 0.01);
        assertEquals(15.0, result.get("Cloudberry"), 0.01);
        assertEquals(5.0, result.get("TeamViewer"), 0.01);
    }

    /**
     * Prepare a list of devices
     *
     * @return A list of devices' mocks
     */
    private List<Device> prepareDevices()
    {
        List<Device> devices = new ArrayList<>(5);

        devices.add(createDeviceMock(DeviceType.WINDOWS_WORKSTATION, 4.0));
        devices.add(createDeviceMock(DeviceType.WINDOWS_SERVER, 4.0));
        devices.add(createDeviceMock(DeviceType.APPLE_MACKINTOSH, 4.0));
        devices.add(createDeviceMock(DeviceType.APPLE_MACKINTOSH, 4.0));
        devices.add(createDeviceMock(DeviceType.APPLE_MACKINTOSH, 4.0));

        return devices;
    }

    /**
     * Prepare mock of services
     *
     * @return A mock of services
     */
    private List<Service> prepareServices()
    {
        List<Service> services = new ArrayList<>(3);

        Map<DeviceType, Double> costsAntivirus = new HashMap<>();
        Map<DeviceType, Double> costsCloudberry = new HashMap<>();
        Map<DeviceType, Double> costsTeamViewer = new HashMap<>();

        costsAntivirus.put(DeviceType.WINDOWS_SERVER, 5.0);
        costsAntivirus.put(DeviceType.WINDOWS_WORKSTATION, 5.0);
        costsAntivirus.put(DeviceType.APPLE_MACKINTOSH, 7.0);

        costsCloudberry.put(DeviceType.WINDOWS_SERVER, 3.0);
        costsCloudberry.put(DeviceType.WINDOWS_WORKSTATION, 3.0);
        costsCloudberry.put(DeviceType.APPLE_MACKINTOSH, 3.0);

        costsTeamViewer.put(DeviceType.WINDOWS_SERVER, 1.0);
        costsTeamViewer.put(DeviceType.WINDOWS_WORKSTATION, 1.0);
        costsTeamViewer.put(DeviceType.APPLE_MACKINTOSH, 1.0);

        services.add(createServiceMock("Antivirus", costsAntivirus));
        services.add(createServiceMock("Cloudberry", costsCloudberry));
        services.add(createServiceMock("TeamViewer", costsTeamViewer));

        return services;
    }

    /**
     * Create mock of device
     *
     * @param type Type of device
     * @param cost Cost of device
     * @return     A mock of device
     */
    private Device createDeviceMock(DeviceType type, Double cost)
    {
        Device device = mock(Device.class);

        when(device.getType()).thenReturn(type);
        when(device.getDeviceCost()).thenReturn(cost);

        return device;
    }

    /**
     * Create mock of service
     *
     * @param name   Name
     * @param costs A map of costs for device types
     * @return A mock of service
     */
    private Service createServiceMock(String name, Map<DeviceType, Double> costs)
    {
        Service service = mock(Service.class);
        when(service.getName()).thenReturn(name);

        for (Map.Entry<DeviceType, Double> entry: costs.entrySet())
        {
            ServiceCost cost = mock(ServiceCost.class);

            when(cost.getAmount()).thenReturn(entry.getValue());
            when(cost.getType()).thenReturn(entry.getKey());

            when(service.getCost(eq(entry.getKey()))).thenReturn(cost);
        }

        return service;
    }
}
