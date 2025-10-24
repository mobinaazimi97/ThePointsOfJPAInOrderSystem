package com.mftplus.ordersofcustomer.controller;


import com.mftplus.ordersofcustomer.entity.Customer;
import com.mftplus.ordersofcustomer.service.CustomerService;
import com.mftplus.ordersofcustomer.utils.Loggable;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Path("/customers")
@Slf4j
public class CustomerController {

    @Inject
    private CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object getCustomers() {
        log.info("Get Customers");
        return customerService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Loggable
    public Object getCustomerById(@PathParam("id") Long id) {
        return customerService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object addCustomer(@Valid Customer customer) {
        customerService.save(customer);
        return customer;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object updateCustomer(@Valid Customer customer) {
        customerService.edit(customer);
        return customer;
    }

    @DELETE
    @Path("/{id}")
    @Loggable
    public Object deleteCustomer(@PathParam("id") Long id) {
        customerService.remove(id);
        return id;
    }

}
