package com.rmm.rmmservicesserverapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A customer of the system
 */
@Entity
@Table(name = "customer")
public class Customer
{
    @Id
    @SequenceGenerator(
        name = "rmm_customer_seq",
        sequenceName = "rmm_customer_seq"
    )
    @GeneratedValue(
        strategy  = GenerationType.SEQUENCE,
        generator = "rmm_customer_seq"
    )
    @Column
    private int id;

    /**
     * Devices added by customer
     */
    @OneToMany(mappedBy = "owner", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JsonIgnore
    private Set<Device> devices = new HashSet<>();

    /**
     * Services chosen by customer
     */
    @ManyToMany
    @JsonIgnore
    private Set<Service> services = new HashSet<>();

    /**
     * Get ID
     *
     * @return ID
     */
    public int getId()
    {
        return id;
    }

    /**
     * Get all added devices
     *
     * @return An iterator of devices
     */
    public Iterable<Device> getDevices()
    {
        return devices;
    }

    /**
     * Add new device
     *
     * @param device A device to add
     */
    public void addDevice(Device device)
    {
        devices.add(device);
    }

    /**
     * Remove previously added device
     *
     * @param device A device to remove
     */
    public void removeDevice(Device device)
    {
        devices.remove(device);
    }

    /**
     * Get all added services
     *
     * @return An iterator of devices
     */
    public Iterable<Service> getServices()
    {
        return services;
    }

    /**
     * Service has been previously added
     *
     * @param service
     * @return
     */
    public boolean hasService(Service service)
    {
        return services.contains(service);
    }

    /**
     * Add new service
     *
     * @param service A service to add
     */
    public void addService(Service service)
    {
        services.add(service);
    }

    /**
     * Remove previously added service
     *
     * @param service A service to remove
     */
    public void removeService(Service service)
    {
        services.remove(service);
    }
}
