package com.mftplus.ordersofcustomer.controller;

import com.mftplus.ordersofcustomer.entity.ProductPropertyValue;
import com.mftplus.ordersofcustomer.service.ProductPropertyValueService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Path("/values")
@Slf4j
public class ProPropertyValueApi {
    @Inject
    private ProductPropertyValueService productPropertyValueService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getValues() {
        log.info("Get all property values");
        return productPropertyValueService.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getValuesById(@PathParam("id") Long id) {
        log.info("Get property value by id: {}", id);
        return productPropertyValueService.findById(id);
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getValuesByName(@PathParam("name") String name) {
        log.info("Get property value by name: {}", name);
        return productPropertyValueService.findByName(name);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object addValues(@Valid ProductPropertyValue productPropertyValue) {
        log.info("Save property value: {}", productPropertyValue);
        productPropertyValueService.save(productPropertyValue);
        return productPropertyValue;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object updateValues(@Valid ProductPropertyValue productPropertyValue) {
        log.info("Update property value: {}", productPropertyValue);
        productPropertyValueService.edit(productPropertyValue);
        return productPropertyValue;
    }

    @DELETE
    @Path("{id}")
    public Object removeValueById(@PathParam("id") Long id) {
        log.info("remove value By Id");
        ProductPropertyValue productPropertyValue = productPropertyValueService.findById(id);
        productPropertyValueService.remove(id);
        return productPropertyValue.getId();
    }
}
