package com.rmm.rmmservicesserverapp.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rmm.rmmservicesserverapp.domain.model.Service;
import com.rmm.rmmservicesserverapp.domain.model.ServiceCost;

import java.util.HashMap;
import java.util.Map;

/**
 * Representation of service data
 */
public class ServiceView
{
    private final Service service;
    
    public ServiceView(Service service)
    {
        this.service = service;
    }

    @JsonProperty("id")
    public int getId()
    {
        return service.getId();
    }

    @JsonProperty("name")
    public String getName()
    {
        return service.getName();
    }

    @JsonProperty("costs")
    public Map<String, Double> getCost()
    {
        Map<String, Double> costs = new HashMap<>();

        for (ServiceCost cost: service.getAllCosts()) {
            costs.put(cost.getType().name(), cost.getAmount());
        }

        return costs;
    }
}
