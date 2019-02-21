package com.rmm.rmmservicesserverapp.domain.service;

import com.rmm.rmmservicesserverapp.domain.model.Customer;
import com.rmm.rmmservicesserverapp.domain.model.Device;
import com.rmm.rmmservicesserverapp.domain.model.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * General implementation of the cost calculator
 */
@org.springframework.stereotype.Service
public final class CostCalculatorGeneral implements CostCalculator
{
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Double> calculateCost(Customer customer)
    {
        Map<String, Double> cost = calculateServicesCost(customer);

        cost.put("Devices", calculateDeviceCost(customer));

        Double total = cost
            .values()
            .stream()
            .reduce(0.0, (a, b) -> a + b);

        cost.put("Output", total);

        return cost;
    }

    /**
     * Calculate cost of services
     *
     * @param customer Customer with chosen services
     * @return A map of costs (service: amount)
     */
    private Map<String, Double> calculateServicesCost(Customer customer)
    {
        Iterable<Device> devices = customer.getDevices();
        Map<String, Double> cost = new HashMap<>();

        for (Service service: customer.getServices()) {
            for (Device device: devices) {
                String name   = service.getName();
                Double amount = service.getCost(device.getType()).getAmount();

                cost.put(name, cost.getOrDefault(name, 0.0) + amount);
            }
        }

        return cost;
    }

    /**
     * Calculate cost of devices
     *
     * @param customer Customer owns devices
     * @return Calculated cost
     */
    private Double calculateDeviceCost(Customer customer)
    {
        double cost = 0.0;

        for (Device device: customer.getDevices()) {
            cost += device.getDeviceCost();
        }

        return cost;
    }
}
