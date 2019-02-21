package com.rmm.rmmservicesserverapp.domain.model;

import javax.persistence.*;

/**
 * Cost for certain device type
 */
@Entity
@Table(name = "service_cost", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"type", "service_id"})
})
public class ServiceCost
{
    @Id
    @SequenceGenerator(
        name = "rmm_service_cost_seq",
        sequenceName = "rmm_service_cost_seq"
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "rmm_service_cost_seq"
    )
    @Column
    private int id;

    /**
     * Type of device
     */
    @Column
    private DeviceType type;

    /**
     * Cost
     */
    @Column
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    public ServiceCost() {}

    public ServiceCost(Service service, DeviceType type, Double amount)
    {
        this.service = service;
        this.type    = type;
        this.amount  = amount;
    }

    public int getId() {
        return id;
    }

    /**
     * Set type of device
     *
     * @param type Type of device
     */
    public void setType(DeviceType type) {
        this.type = type;
    }

    /**
     * Get type of device
     *
     * @return Type of device
     */
    public DeviceType getType() {
        return type;
    }

    /**
     * Set amount
     *
     * @param amount Amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Get amount
     *
     * @return Amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Get service-owner of the cost
     *
     * @return
     */
    public Service getService()
    {
        return service;
    }
}