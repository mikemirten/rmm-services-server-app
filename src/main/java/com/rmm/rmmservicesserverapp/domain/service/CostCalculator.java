package com.rmm.rmmservicesserverapp.domain.service;

import com.rmm.rmmservicesserverapp.domain.model.Customer;

import java.util.Map;

/**
 * Interface of a cost calculator
 */
public interface CostCalculator
{
    /**
     * Calculate cost
     *
     * {
     *     "service_1": (Double) value,
     *     "service_2": (Double) value
     * }
     *
     * @param customer Customer to calculate cost
     * @return         A map of costs
     */
    Map<String, Double> calculateCost(Customer customer);
}
