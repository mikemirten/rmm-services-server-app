package com.rmm.rmmservicesserverapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * A services of the system available for customers
 */
@Entity
@Table(name = "service")
public class Service
{
    @Id
    @SequenceGenerator(
        name = "rmm_service_seq",
        sequenceName = "rmm_service_seq"
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "rmm_service_seq"
    )
    @Column
    private int id;

    /**
     * Name of service
     */
    @Column
    private String name;

    /**
     * Costs of service
     */
    @OneToMany(mappedBy = "service", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    @MapKey(name = "type")
    @JsonIgnore
    private Map<DeviceType, ServiceCost> cost = new HashMap<>();

    public Service() {}

    public Service(String name)
    {
        this.name = name;
    }

    /**
     * Get ID
     *
     * @return ID of service
     */
    public int getId()
    {
        return id;
    }

    /**
     * Set name
     *
     * @param name New name of service
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Get name
     *
     * @return Current name of service
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set cost
     *
     * @param cost New cost of service
     */
    public void setCost(ServiceCost cost)
    {
        this.cost.put(cost.getType(), cost);
    }

    /**
     * Get cost
     *
     * @return Current cost of service
     */
    public ServiceCost getCost(DeviceType type)
    {
        return cost.get(type);
    }

    /**
     * Get all costs
     *
     * @return Iterator of all costs
     */
    public Iterable<ServiceCost> getAllCosts()
    {
        return cost.values();
    }
}
