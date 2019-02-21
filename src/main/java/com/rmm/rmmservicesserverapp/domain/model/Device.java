package com.rmm.rmmservicesserverapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * A customer's device
 */
@Entity
@Table(name = "device")
public class Device
{
    /**
     * Fixed cost of device
     */
    private static final double DEVICE_COST = 4.0;

    @Id
    @SequenceGenerator(
        name = "rmm_device_seq",
        sequenceName = "rmm_device_seq"
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "rmm_device_seq"
    )
    @Column
    private int id;

    /**
     * Name of device
     */
    @Column
    private String name;

    /**
     * Type of device
     */
    @Column
    private DeviceType type;

    /**
     * A customer owning the device
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Customer owner;

    public Device() {}

    public Device(Customer owner, DeviceType type, String name)
    {
        this.owner = owner;
        this.type  = type;
        this.name  = name;
    }

    /**
     * Get ID
     *
     * @return ID of device
     */
    public int getId()
    {
        return id;
    }

    /**
     * Set name
     *
     * @param name New name of device
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Get name
     *
     * @return current name of device
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set type
     *
     * @param type New type of device
     */
    public void setType(DeviceType type)
    {
        this.type = type;
    }

    /**
     * Get type
     *
     * @return Current type of device
     */
    public DeviceType getType()
    {
        return type;
    }

    /**
     * Get owner
     *
     * @return Owner of the device
     */
    public Customer getOwner()
    {
        return owner;
    }

    /**
     * Get cost of device
     *
     * @return Device cost
     */
    public double getDeviceCost()
    {
        return DEVICE_COST;
    }
}
