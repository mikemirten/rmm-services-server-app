package com.rmm.rmmservicesserverapp.controller;

import com.rmm.rmmservicesserverapp.domain.model.Customer;
import com.rmm.rmmservicesserverapp.domain.model.Service;
import com.rmm.rmmservicesserverapp.jsonapi.DataResponse;
import com.rmm.rmmservicesserverapp.domain.service.CostCalculator;
import com.rmm.rmmservicesserverapp.service.CustomerService;
import com.rmm.rmmservicesserverapp.service.ServiceService;
import com.rmm.rmmservicesserverapp.view.ServiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ServiceController
{
    /**
     * Service of services
     */
    private final ServiceService serviceService;

    /**
     * Customer's service
     */
    private final CustomerService customerService;

    /**
     * Calculator of cost
     */
    private final CostCalculator costCalculator;

    @Autowired
    public ServiceController(
        ServiceService  serviceService,
        CustomerService customerService,
        CostCalculator  costCalculator
    ) {
        this.customerService = customerService;
        this.serviceService  = serviceService;
        this.costCalculator  = costCalculator;
    }

    /**
     * Get list of all available services
     *
     * @return Response with a list of services
     */
    @GetMapping("/services")
    public DataResponse<Iterable<ServiceView>> available()
    {
        Iterable<Service> services = serviceService.all();
        List<ServiceView> views    = new LinkedList<>();

        for (Service service: services) {
            views.add(new ServiceView(service));
        }

        return new DataResponse<>(views);
    }

    /**
     * Get list of selected services by certain customer
     *
     * @param id ID of customer
     * @return   Response with a list of services
     */
    @GetMapping("/customers/{id}/services")
    public DataResponse<Iterable<Service>> selected(@PathVariable int id)
    {
        Iterable<Service> services = customerService.find(id).getServices();

        return new DataResponse<>(services);
    }

    /**
     * Add service required by customer
     *
     * @param customerId ID of customer
     * @param serviceId  ID of service
     */
    @PostMapping("/customers/{customerId}/services/{serviceId}")
    public void add(@PathVariable int customerId, @PathVariable int serviceId)
    {
        Customer customer = customerService.find(customerId);
        Service  service  = serviceService.find(serviceId);

        customerService.addService(customer, service);
    }

    /**
     * Remove service cancelled by customer
     *
     * @param customerId ID of customer
     * @param serviceId  ID of service
     */
    @DeleteMapping("/customers/{customerId}/services/{serviceId}")
    public void remove(@PathVariable int customerId, @PathVariable int serviceId)
    {
        Customer customer = customerService.find(customerId);
        Service  service  = serviceService.find(serviceId);

        customerService.cancelService(customer, service);
    }

    /**
     * Calculate cost of services
     *
     * @param id ID of customer
     * @return   Response with a map(name: amount) of costs
     */
    @GetMapping("/customers/{id}/calculate-cost")
    public DataResponse<Map<String, Double>> calculate(@PathVariable int id)
    {
        Customer customer = customerService.find(id);
        Map<String, Double> cost = costCalculator.calculateCost(customer);

        return new DataResponse<>(cost);
    }
}
